package com.samir.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.samir.dto.ChatGPTRequest;
import com.samir.dto.ChatGptResponse;
import com.samir.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ChatGPTApiServiceImpl implements ChatGPTApiService {

    private final RestTemplate template;
    @Value("${openai.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;

    @Value("${openai.api.temperature}")
    private Double temperature;

    @Override
    public String ChatGPTApiResponse(String prompt, UserRequest userRequest) {
        ChatGPTRequest request = new ChatGPTRequest(model, prompt, temperature);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);

        String jsonResponse = null;

        try {
            if (chatGptResponse != null && chatGptResponse.getChoices() != null && !chatGptResponse.getChoices().isEmpty()) {
                jsonResponse = chatGptResponse.getChoices().get(0).getMessage().getContent();
            }

            if (jsonResponse != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonArray = objectMapper.readTree(jsonResponse);

                for (JsonNode jsonObject : jsonArray) {
                    ((ObjectNode) jsonObject).put("realm", userRequest.getRealm());
                    ((ObjectNode) jsonObject).put("fromTargetExp", userRequest.getFromTargetExp());
                    ((ObjectNode) jsonObject).put("toTargetExp", userRequest.getToTargetExp());
                    ((ObjectNode) jsonObject).put("fromDifficulty", userRequest.getFromDifficulty());
                    ((ObjectNode) jsonObject).put("toDifficulty", userRequest.getToDifficulty());
                }

                jsonResponse = jsonArray.toString();
            }
        } catch (IOException e) {
            jsonResponse = "Error: An exception occurred while processing the response from the ChatGPT API.";
            e.printStackTrace();
        }

        if (jsonResponse == null) {
            jsonResponse = "Error: No response from the ChatGPT API.";
        }

        System.out.println("Output Prompt: " + jsonResponse);
        return jsonResponse;
    }
}
