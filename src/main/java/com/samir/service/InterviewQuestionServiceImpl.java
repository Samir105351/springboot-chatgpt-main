package com.samir.service;

import com.samir.dto.ChatGptApiResponse;
import com.samir.dto.InterviewQuestionCreationRequest;
import com.samir.entity.InterviewQuestion;
import com.samir.utils.ConvertToJSON;
import com.samir.utils.JSONtoInterviewQuestionList;
import com.samir.utils.PromptUtil;
import com.samir.utils.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewQuestionServiceImpl implements InterviewQuestionService {

    private final ChatGPTApiService chatGPTApiService;

    private final DatabaseService databaseService;

    @Override
    public List<InterviewQuestion> createInterviewQuestions(InterviewQuestionCreationRequest request, BindingResult bindingResult){
        Validation.bindingErrorChecking(bindingResult);
        String prompt = PromptUtil.createInterviewQuesPrompt(request);
        ChatGptApiResponse apiResponse = chatGPTApiService.prompt(prompt, 0.7);
        String jsonApiResponse=apiResponse.getChoices().get(0).getMessage().getContent();
        String apiResponseToJSON = ConvertToJSON.responseToJSON(jsonApiResponse, request);
        List<InterviewQuestion> interviewQuestionList = JSONtoInterviewQuestionList.interviewQuestionList(apiResponseToJSON);
        return databaseService.save(interviewQuestionList);
    }

    @Override
    public List<InterviewQuestion> getInterviewQuestions() {
        return databaseService.getAllInterviewQuestions();
    }

    @Override
    public List<InterviewQuestion> getRandomInterviewQuestions(InterviewQuestionCreationRequest interviewQuestionCreationRequest, BindingResult bindingResult) {
        Validation.bindingErrorChecking(bindingResult);
        return databaseService.getRandomInterviewQuestions(interviewQuestionCreationRequest);
    }
}
