package com.samir.service;

import com.samir.dto.InterviewQuestionCreationRequest;
import com.samir.entity.InterviewQuestion;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DatabaseService {
    List<InterviewQuestion> save(List<InterviewQuestion> interviewQuestions);

    List<InterviewQuestion> getAllInterviewQuestions();

    List<InterviewQuestion> getRandomInterviewQuestions(InterviewQuestionCreationRequest interviewQuestionCreationRequest);
}
