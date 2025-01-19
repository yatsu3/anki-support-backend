package com.example.demo.domain.service;
import com.example.demo.domain.entity.GroupedQuestion;
import com.example.demo.domain.entity.Question;
import com.example.demo.infrastructure.dto.QuestionDto;
import com.example.demo.infrastructure.repository.CategoryRepository;
import com.example.demo.infrastructure.repository.QuestionRepository;
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
    ChoiceService choiceService;
    
    public void registerQuestion(String categoryName, int userId, String questionContent, List<String> choices, int correctedAnswer, String explanation) {

        int questionId = questionRepository.getNextId();
        int categoryId = categoryRepository.getIdByCategoryName(categoryName);
        Question question = new Question(questionId, userId, categoryId, questionContent, explanation, correctedAnswer);

        questionRepository.registerQuestion(question);
        
        choiceService.registerChoice(choices, questionId);

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