package com.samir.controller;

import com.samir.dto.InterviewQuestionCreationRequest;
import com.samir.entity.InterviewQuestion;
import com.samir.exception.ApiException;
import com.samir.service.InterviewQuestionService;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@RequiredArgsConstructor
public class InterviewQuestionController {

    private final InterviewQuestionService interviewQuestionService;

    @GetMapping("/get-all-interview-questions")
    public List<InterviewQuestion> getAllInterviewQuestions() {
        return interviewQuestionService.getInterviewQuestions();
    }

    @GetMapping("/get-random-interview-questions")
    public List<InterviewQuestion> getRandomInterviewQuestions(@Valid InterviewQuestionCreationRequest request, BindingResult bindingResult) {
        return interviewQuestionService.getRandomInterviewQuestions(request,bindingResult);
    }

    @PostMapping("/create-interview-questions")
    public List<InterviewQuestion> createInterviewQuestions(@RequestBody InterviewQuestionCreationRequest request) {
        return interviewQuestionService.createInterviewQuestions(request);
    }
}
