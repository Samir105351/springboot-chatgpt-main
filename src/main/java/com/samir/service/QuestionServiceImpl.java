package com.samir.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samir.Repository.CodingQuestionRepository;
import com.samir.entity.CodingQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private CodingQuestionRepository codingQuestionRepository;

    @Override
    public List<CodingQuestion> save(String GPTResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(GPTResponse);

            if (jsonNode.isArray()) {
                List<CodingQuestion> codingQuestions = new ArrayList<>();
                for (JsonNode objectNode : jsonNode) {
                    // Parse each JSON object into a CodingQuestion object
                    CodingQuestion codingQuestion = objectMapper.treeToValue(objectNode, CodingQuestion.class);

                    // Save the CodingQuestion entity to the list
                    codingQuestions.add(codingQuestion);
                }

                // Save the list of CodingQuestion entities to the database
                return codingQuestionRepository.saveAll(codingQuestions);
            } else {
                // Handle the case where the input is not an array of JSON objects
                throw new IllegalArgumentException("Input is not an array of JSON objects.");
            }
        } catch (IOException e) {
            // Handle any exceptions, e.g., JSON parsing error
            e.printStackTrace();
            return new ArrayList<>(); // You may want to handle this better in your application
        }
    }

    @Override
    public List<CodingQuestion> getAllCodingQuestions() {
        return codingQuestionRepository.findAll();
    }
}
