package com.samir.service;

import com.samir.entity.InterviewQuestion;

import java.util.List;

public interface DataBaseService {
    List<InterviewQuestion> save(String GPTResponse);

    List<InterviewQuestion> getAllCodingQuestions();
}
