package com.samir.service;

import com.samir.Repository.InterviewQuestionRepository;
import com.samir.dto.InterviewQuestionCreationRequest;
import com.samir.entity.InterviewQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    @Override
    public List<InterviewQuestion> getRandomInterviewQuestions(InterviewQuestionCreationRequest interviewQuestionCreationRequest) {
        return interviewQuestionRepository.getRandomQuestions(interviewQuestionCreationRequest);
    }
}
