package com.samir.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samir.entity.InterviewQuestion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONtoInterviewQuestionList {
    public static List<InterviewQuestion> interviewQuestionList(String gptResponse){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(gptResponse);

            if (jsonNode.isArray()) {
                List<InterviewQuestion> interviewQuestions = new ArrayList<>();
                List<InterviewQuestion> invalidInterviewQuestions = new ArrayList<>();

                for (JsonNode objectNode : jsonNode) {
                    try {
                        InterviewQuestion interviewQuestion = objectMapper.treeToValue(objectNode, InterviewQuestion.class);
                        interviewQuestions.add(interviewQuestion);
                    } catch (JsonProcessingException e) {

                        e.printStackTrace();

                    }
                }
                return interviewQuestions;
            } else {
                throw new IllegalArgumentException("Input is not an array of JSON objects.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
