package com.example.demo.domain.service;
import com.example.demo.domain.entity.GroupedQuestion;
import com.example.demo.domain.entity.Question;
import com.example.demo.infrastructure.dto.QuestionDto;
import com.example.demo.infrastructure.repository.AnswerRepository;
import com.example.demo.infrastructure.repository.CategoryRepository;
import com.example.demo.infrastructure.repository.QuestionRepository;
import com.example.demo.presentation.QuestionRequest;
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
    ChoiceService choiceService;

    @Autowired
    AnswerService answerService;
    
    public void registerQuestion(QuestionRequest request) {

        int questionId = questionRepository.getNextId();
        int categoryId = categoryRepository.getIdByCategoryName(request.getCategoryName());
        int answerId = answerRepository.getNextId();

        Question question = new Question(questionId, request.getUserId(), categoryId, answerId, request.getQuestionContent(), request.getExplanation());
        questionRepository.registerQuestion(question);

        choiceService.registerChoice(request.getChoicesData(), questionId);
        answerService.registerAnswer(answerId, questionId, request.getAnswer());

    }

    public List<GroupedQuestion> getQuestion(int userId, int categoryId) {
        List<QuestionDto> list = questionRepository.getQuestion(userId, categoryId);
        
        List<GroupedQuestion> aaa = converter(list);
        
        return aaa;
    }

    private List<GroupedQuestion> converter(List<QuestionDto> list) {
        return list.stream()
            .collect(Collectors.groupingBy(
                QuestionDto::getQuestionId, // キーとなる questionId
                LinkedHashMap::new, // 順番を保持するため LinkedHashMap を使用（任意）
                Collectors.collectingAndThen(Collectors.toList(), questionList -> 
                    new GroupedQuestion(
                        questionList.get(0).getQuestionId(),
                        questionList.get(0).getQuestionContent(),
                        questionList.stream().map(QuestionDto::getChoiceContent).collect(Collectors.toList()),
                        questionList.get(0).getCorrectedAnswer(),
                        questionList.get(0).getExplanation()
                    )
                )
            ))
            .values()
            .stream()
            .collect(Collectors.toList());
    }
}