package com.samir.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.samir.dto.ChatGptApiResponse;
import com.samir.dto.InterviewQuestionCreationRequest;
import com.samir.exception.ApiException;
import org.springframework.http.HttpStatus;

public class StringToJSON {
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
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "An exception occurred while processing the response from the ChatGPT API.");
        }

        return jsonResponse;
    }

    public static String jsonFormatter(String json){
        if(json==null) return "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            return jsonNode.path("error").path("message").asText();
        } catch (Exception e) {
            return "";
        }
    }
}
