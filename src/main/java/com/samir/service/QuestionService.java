package com.samir.service;

import com.samir.entity.CodingQuestion;

import java.util.List;

public interface QuestionService {
    List<CodingQuestion> save(String GPTResponse);
    List<CodingQuestion> getAllCodingQuestions();
}
