package com.samir.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Validation {
    public static String errorMessage(String json) {
        if (json == null) {
            return "";
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            return jsonNode.path("error").path("message").asText();
        } catch (Exception e) {
            return "";
        }
    }
}
