package com.samir.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.samir.dto.InterviewQuestionCreationRequest;
import com.samir.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ConvertToJSON {
    public static String responseToJSON(String jsonResponse, InterviewQuestionCreationRequest request) {
        try {
            if (jsonResponse != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonArray = objectMapper.readTree(jsonResponse);

                ObjectNode template = JsonTemplate.createQuestionTemplate(request);

                ArrayNode updatedArray = objectMapper.createArrayNode();

                for (JsonNode jsonObject : jsonArray) {
                    if (jsonObject instanceof ObjectNode objNode) {
                        ObjectNode mergedObject = template.deepCopy();
                        mergedObject.setAll(objNode);
                        updatedArray.add(mergedObject);
                    }
                }

                jsonResponse = updatedArray.toString();
                System.out.println("jsonResponse= " + jsonResponse);
            }
            else{
                throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "An exception occurred while processing the response from the ChatGPT API.");
            }
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "An exception occurred while processing the response from the ChatGPT API.");
        }

        return jsonResponse;
    }



    public static String errorMessage(String json){
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
