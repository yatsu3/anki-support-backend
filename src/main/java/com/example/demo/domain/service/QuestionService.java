package com.example.demo.domain.service;
import com.example.app.application.dto.QuestionResponse;
import com.example.demo.domain.entity.Choice;
import com.example.demo.domain.entity.GroupedQuestion;
import com.example.demo.domain.entity.Question;
import com.example.demo.infrastructure.dto.AnswerDto;
import com.example.demo.infrastructure.dto.CategoryDto;
import com.example.demo.infrastructure.dto.CategoryInfoDto;
import com.example.demo.infrastructure.dto.ChoiceDto;
import com.example.demo.infrastructure.dto.GroupedQuestionDto;
import com.example.demo.infrastructure.dto.QuestionDto;
import com.example.demo.infrastructure.dto.QuestionInfoDto;
import com.example.demo.infrastructure.repository.AnswerRepository;
import com.example.demo.infrastructure.repository.CategoryRepository;
import com.example.demo.infrastructure.repository.ChoiceRepository;
import com.example.demo.infrastructure.repository.QuestionRepository;
import com.example.demo.presentation.QuestionRequest;
import com.example.demo.presentation.UpdateQuestionRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    ChoiceRepository choiceRepository;

    @Autowired
    ChoiceService choiceService;
    @Autowired
    AnswerService answerService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;
    
    public void registerQuestion(QuestionRequest request) {

        int userId = userService.getUserIdByUuid(request.getUuid());
        int questionId = questionRepository.getNextId();
        int categoryId = categoryRepository.getIdByCategoryName(request.getCategoryName(), userId);
        int answerId = answerRepository.getNextId();

        Question question = new Question(questionId, userId, categoryId, answerId, request.getQuestionContent(), request.getExplanation());
        questionRepository.registerQuestion(question);

        choiceService.registerChoice(request.getChoicesData(), questionId);
        answerService.registerAnswer(answerId, questionId, request.getAnswer());

    }

    public List<GroupedQuestion> getQuestion(String uuid, int categoryId) {
        int userId = userService.getUserIdByUuid(uuid);
        List<GroupedQuestionDto> list = questionRepository.getQuestion(userId, categoryId);
        
        List<GroupedQuestion> aaa = converter(list);
        
        return aaa;
    }

    private List<GroupedQuestion> converter(List<GroupedQuestionDto> list) {
        return list.stream()
            .collect(Collectors.groupingBy(
                GroupedQuestionDto::getQuestionId, // キーとなる questionId
                LinkedHashMap::new, // 順番を保持するため LinkedHashMap を使用（任意）
                Collectors.collectingAndThen(Collectors.toList(), questionList -> 
                    new GroupedQuestion(
                        questionList.get(0).getQuestionId(),
                        questionList.get(0).getQuestionContent(),
                        questionList.stream().map(GroupedQuestionDto::getChoiceContent).collect(Collectors.toList()),
                        questionList.get(0).getCorrectedAnswer(),
                        questionList.get(0).getExplanation()
                    )
                )
            ))
            .values()
            .stream()
            .collect(Collectors.toList());
    }

    public List<QuestionDto> getQuestionList(String uuid, int questionId) {
        int userId = userService.getUserIdByUuid(uuid);
        return questionRepository.getQuestionList(userId, questionId);
    }

    public QuestionResponse getQuestionById(int questionId, String uuid) {
        // カテゴリー情報を取得
        CategoryInfoDto category = categoryService.findCategoryById(questionId);

        // ユーザー情報を取得
        int userId = userService.getUserIdByUuid(uuid);

        // 問題情報を取得
        QuestionInfoDto question = questionRepository.findQuestionById(questionId, userId);
        // 選択肢情報を取得
        List<ChoiceDto> choiceList = choiceService.searchChoicesByQuestionId(questionId);

        // 解答情報を取得
        AnswerDto answer = answerService.findAnswerByQuestionId(questionId);
        // response型に詰めて返す
        return new QuestionResponse(questionId, category.getCategoryName(), question.getQuestionContent(), choiceList, answer.getAnswerContent(), question.getExplanation());

    }

    public void updateQuestion(UpdateQuestionRequest request) {

        int userId = userService.getUserIdByUuid(request.getUuid());

        int categoryId = categoryRepository.getIdByCategoryName(request.getCategoryName(), userId);


        // 問題文と解説文が変更されている場合、更新
        QuestionInfoDto questionInfo = questionRepository.findQuestionById(request.getQuestionId(), userId);
        if (questionInfo.getQuestionContent() != request.getExplanation()) {
            questionRepository.updateQuestionInfo(request.getQuestionId(), request.getQuestionContent(), request.getExplanation());
        }

        // 選択肢が変更されている場合、更新
        List<ChoiceDto> requestedChoiceList = request.getChoicesData();
        List<ChoiceDto> choiceList = choiceService.searchChoicesByQuestionId(request.getQuestionId());
        int minSize = Math.min(requestedChoiceList.size(), choiceList.size());
        for(int i = 0; i < minSize; i++) {
            if (!requestedChoiceList.get(i).equals(choiceList.get(i))) {
                // 更新
                choiceService.updateChoice(requestedChoiceList.get(i), request.getQuestionId());
            }
        }

        // 変更後の選択肢の方が多い場合
        if (requestedChoiceList.size() > choiceList.size()) {
            List<ChoiceDto> chocieDtoList = requestedChoiceList.subList(minSize, requestedChoiceList.size());
            for(ChoiceDto choiceDto : chocieDtoList) {
                // 新規ID採番
                int choiceId = choiceRepository.getNextId();
                Choice choice = new Choice(choiceId, request.getQuestionId(), choiceDto.getChoiceContent());
                choiceRepository.registerChoice(choice);
            }
        }

        // 変更前の選択肢の方が多い場合
        if (requestedChoiceList.size() < choiceList.size()) {
            List<ChoiceDto> chocieDtoList = choiceList.subList(minSize, choiceList.size());
            for(ChoiceDto choiceDto : chocieDtoList) {
                // 削除
                choiceService.deleteChoice(choiceDto.getChoiceId());
            }
        }

        AnswerDto answerDto = answerService.findAnswerByQuestionId(request.getQuestionId());



        
        answerService.updateAnswer(answerDto.getAnswerId(), request.getQuestionId(), request.getAnswer());

    }
}