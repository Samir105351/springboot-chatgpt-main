package com.samir.service;

import com.samir.Repository.QuestionRepository;
import com.samir.dto.QuestionCreationRequest;
import com.samir.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseServiceImpl implements DatabaseService {

    private final QuestionRepository questionRepository;

    @Override
    public List<Question> save(List<Question> questions) {
        return questionRepository.saveAll(questions);
    }

    @Override
    public List<Question> getAllInterviewQuestions() {
        return questionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

}
