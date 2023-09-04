package com.samir.service;

import com.samir.dto.ChatGptApiResponse;
import com.samir.dto.InterviewQuestionCreationRequest;
import com.samir.entity.InterviewQuestion;
import com.samir.utils.InterviewJsonFormatter;
import com.samir.utils.JSONtoInterviewQuestionList;
import com.samir.utils.PromptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewQuestionServiceImpl implements InterviewQuestionService {

    private final ChatGPTApiService chatGPTApiService;

    private final DatabaseService dataBaseService;

    @Override
    public List<InterviewQuestion> createInterviewQuestions(InterviewQuestionCreationRequest interviewQuestionCreationRequest) {
        String prompt = PromptUtil.createInterviewQuestionPrompt(interviewQuestionCreationRequest);
        ChatGptApiResponse apiResponse = chatGPTApiService.prompt(prompt, 0.7);
        String apiResponseToJSON =InterviewJsonFormatter.jsonFormatter(apiResponse,interviewQuestionCreationRequest);
        List<InterviewQuestion> interviewQuestionList= JSONtoInterviewQuestionList.interviewQuestionList(apiResponseToJSON);
        return dataBaseService.save(interviewQuestionList);
    }
    @Override
    public List<InterviewQuestion> getInterviewQuestions(){
        return dataBaseService.getAllInterviewQuestions();
    }
}
