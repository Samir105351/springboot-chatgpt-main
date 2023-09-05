package com.samir.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samir.entity.InterviewQuestion;
import com.samir.exception.ApiException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class JSONtoInterviewQuestionList {
    public static List<InterviewQuestion> interviewQuestionList(String gptResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(gptResponse);

            List<InterviewQuestion> interviewQuestions = new ArrayList<>();

            for (JsonNode objectNode : jsonNode) {
                InterviewQuestion interviewQuestion = objectMapper.treeToValue(objectNode, InterviewQuestion.class);
                interviewQuestions.add(interviewQuestion);
            }

            return interviewQuestions;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "An exception occurred while converting JSON to InterviewQuestionList");
        }
    }
}
