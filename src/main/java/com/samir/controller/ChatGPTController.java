package com.samir.controller;

import com.samir.dto.UserRequest;
import com.samir.entity.InterviewQuestion;
import com.samir.service.PromptService;
import com.samir.service.DataBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class ChatGPTController {

    private final DataBaseService dataBaseService;

    private final PromptService promptService;


    @GetMapping("/coding-questions")
    public List<InterviewQuestion> GetAllCodingQuestions() {
        return dataBaseService.getAllCodingQuestions();
    }

    @PostMapping("/create")
    public List<InterviewQuestion> createJSONQuestionListUsingChatGPT(@RequestBody UserRequest userRequest) {
        return promptService.UserPrompt(userRequest);
    }
}