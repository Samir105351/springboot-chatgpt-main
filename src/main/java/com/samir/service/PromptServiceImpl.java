package com.samir.service;

import com.samir.dto.UserRequest;
import com.samir.entity.InterviewQuestion;
import com.samir.utils.UserPrompt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromptServiceImpl implements PromptService {

    private final ChatGPTApiService chatGPTApiService;
    private final DataBaseService dataBaseService;

    @Override
    public List<InterviewQuestion> UserPrompt(UserRequest userRequest) {
        String GPTResponse = chatGPTApiService.ChatGPTApiResponse(UserPrompt.promptBuilder(userRequest), userRequest);
        return dataBaseService.save(GPTResponse);
    }
}
