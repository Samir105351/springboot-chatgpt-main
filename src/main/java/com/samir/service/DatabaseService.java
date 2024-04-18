package com.samir.service;

import com.samir.dto.ChatGPTAnswerTemplate;
import com.samir.dto.QuestionCreationRequest;
import com.samir.dto.QuestionGetRequest;
import com.samir.entity.Question;

import java.util.List;

public interface DatabaseService {
    List<Question> save(List<Question> questions);

    List<Question> getAllInterviewQuestions();

    List<Question> getRandomQuestions(QuestionGetRequest request);




}
