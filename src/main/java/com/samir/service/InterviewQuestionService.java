package com.samir.service;

import com.samir.dto.InterviewQuestionCreationRequest;
import com.samir.entity.InterviewQuestion;

import java.io.IOException;
import java.util.List;

public interface InterviewQuestionService {
    List<InterviewQuestion> createInterviewQuestions(InterviewQuestionCreationRequest interviewQuestionCreationRequest);

    List<InterviewQuestion> getInterviewQuestions();
}
