package com.samir.controller;

import com.samir.dto.InterviewQuestionCreationRequest;
import com.samir.entity.InterviewQuestion;
import com.samir.service.InterviewQuestionService;
import com.samir.service.DatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@RequiredArgsConstructor
public class InterviewQuestionController {

    private final InterviewQuestionService interviewQuestionService;


    @GetMapping("/get-all-interview-questions")
    public List<InterviewQuestion> GetAllInterviewQuestions() {
        return interviewQuestionService.getInterviewQuestions();
    }

    @PostMapping("/create-interview-questions")
    public List<InterviewQuestion> createInterviewQuestions(@RequestBody InterviewQuestionCreationRequest request) {
        return interviewQuestionService.createInterviewQuestions(request);
    }
}