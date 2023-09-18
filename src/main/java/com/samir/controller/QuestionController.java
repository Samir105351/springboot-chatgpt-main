package com.samir.controller;

import com.samir.dto.QuestionCreationRequest;
import com.samir.entity.Question;
import com.samir.service.QuestionService;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/get-all-interview-questions")
    public List<Question> getAllQuestions() {
        return questionService.getQuestions();
    }


    @PostMapping("/create-questions")
    public List<Question> createInterviewQuestions(@Valid @RequestBody QuestionCreationRequest questionCreationRequest) {
        return questionService.createInterviewQuestions(questionCreationRequest);
    }
}
