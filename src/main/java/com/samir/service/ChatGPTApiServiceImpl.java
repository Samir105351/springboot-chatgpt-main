package com.samir.service;

import com.samir.dto.ChatGPTApiRequest;
import com.samir.dto.ChatGptApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ChatGPTApiServiceImpl implements ChatGPTApiService {

    private final RestTemplate template;
    @Value("gpt-3.5-turbo-16k")
    private String model;

    @Value(("https://api.openai.com/v1/chat/completions"))
    private String apiURL;

    @Override
    public ChatGptApiResponse prompt(String prompt, Double temperature) {

        ChatGPTApiRequest apiRequest = new ChatGPTApiRequest(model, prompt, temperature);
        ChatGptApiResponse apiResponse;
        apiResponse = template.postForObject(apiURL, apiRequest, ChatGptApiResponse.class);

        return apiResponse;
    }
}
