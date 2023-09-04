package com.samir.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.samir.dto.ChatGptApiResponse;
import com.samir.dto.InterviewQuestionCreationRequest;

import java.io.IOException;

public class InterviewJsonFormatter {
    public static String jsonFormatter(ChatGptApiResponse apiResponse, InterviewQuestionCreationRequest interviewQuestionCreationRequest) {

        String jsonResponse = null;

        try {
            if (apiResponse != null && apiResponse.getChoices() != null && !apiResponse.getChoices().isEmpty()) {
                jsonResponse = apiResponse.getChoices().get(0).getMessage().getContent();
            }

            if (jsonResponse != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonArray = objectMapper.readTree(jsonResponse);

                for (JsonNode jsonObject : jsonArray) {
                    ((ObjectNode) jsonObject).put("realm", interviewQuestionCreationRequest.getRealm());
                    ((ObjectNode) jsonObject).put("fromTargetExp", interviewQuestionCreationRequest.getFromTargetExp());
                    ((ObjectNode) jsonObject).put("toTargetExp", interviewQuestionCreationRequest.getToTargetExp());
                    ((ObjectNode) jsonObject).put("fromDifficulty", interviewQuestionCreationRequest.getFromDifficulty());
                    ((ObjectNode) jsonObject).put("toDifficulty", interviewQuestionCreationRequest.getToDifficulty());
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
        return jsonResponse;
    }
}
