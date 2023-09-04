package com.samir.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samir.Repository.InterviewQuestionRepository;
import com.samir.entity.InterviewQuestion;
import com.samir.utils.JSONtoInterviewQuestionList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseServiceImpl implements DatabaseService {

    private final InterviewQuestionRepository interviewQuestionRepository;

    @Override
public List<InterviewQuestion> save(List<InterviewQuestion> interviewQuestions) {
        return interviewQuestionRepository.saveAll(interviewQuestions);
    }

    @Override
    public List<InterviewQuestion> getAllInterviewQuestions() {
        return interviewQuestionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}