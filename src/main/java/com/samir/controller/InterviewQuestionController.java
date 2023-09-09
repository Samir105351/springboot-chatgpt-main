package com.samir.controller;

import com.samir.dto.InterviewQuestionCreationRequest;
import com.samir.entity.InterviewQuestion;
import com.samir.exception.ApiException;
import com.samir.service.InterviewQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            String errorMessage = extractErrorMessage(bindingResult);
            throw new ApiException(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage);
        }
        return interviewQuestionService.getRandomInterviewQuestions(request);
    }

    @PostMapping("/create-interview-questions")
    public List<InterviewQuestion> createInterviewQuestions(@RequestBody InterviewQuestionCreationRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = extractErrorMessage(bindingResult);
            throw new ApiException(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage);
        }
        return interviewQuestionService.createInterviewQuestions(request);
    }

    private String extractErrorMessage(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        System.out.println(fieldErrors);
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : fieldErrors) {
            errorMessage.append(error.getField()).append(" ").append(error.getDefaultMessage()).append("; ");
        }
        return errorMessage.toString();
    }
}
