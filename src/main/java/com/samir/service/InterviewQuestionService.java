package com.samir.service;

import com.samir.dto.InterviewQuestionCreationRequest;
import com.samir.entity.InterviewQuestion;
import org.springframework.validation.BindingResult;

import javax.naming.Binding;
import java.util.List;

public interface InterviewQuestionService {
    List<InterviewQuestion> createInterviewQuestions(InterviewQuestionCreationRequest interviewQuestionCreationRequest,BindingResult bindingResult);

    List<InterviewQuestion> getInterviewQuestions();

    List<InterviewQuestion> getRandomInterviewQuestions(InterviewQuestionCreationRequest interviewQuestion, BindingResult bindingResult);
}
