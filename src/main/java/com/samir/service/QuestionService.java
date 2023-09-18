package com.samir.service;

import com.samir.dto.QuestionCreationRequest;
import com.samir.entity.Question;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface QuestionService {
    List<Question> createInterviewQuestions(QuestionCreationRequest request);

    List<Question> getQuestions();

}
