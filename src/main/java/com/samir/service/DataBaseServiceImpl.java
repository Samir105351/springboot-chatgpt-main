package com.samir.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samir.Repository.InterviewQuestionRepository;
import com.samir.entity.InterviewQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataBaseServiceImpl implements DataBaseService {
    private final InterviewQuestionRepository interviewQuestionRepository;

    @Override
    public List<InterviewQuestion> save(String GPTResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(GPTResponse);

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
                return interviewQuestionRepository.saveAll(interviewQuestions);
            } else {
                throw new IllegalArgumentException("Input is not an array of JSON objects.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<InterviewQuestion> getAllCodingQuestions() {
        return interviewQuestionRepository.findAll();
    }
}
