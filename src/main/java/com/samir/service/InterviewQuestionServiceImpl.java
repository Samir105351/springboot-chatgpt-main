package com.samir.service;

import com.samir.dto.ChatGptApiResponse;
import com.samir.dto.InterviewQuestionCreationRequest;
import com.samir.entity.InterviewQuestion;
import com.samir.exception.ApiException;
import com.samir.utils.StringToJSON;
import com.samir.utils.JSONtoInterviewQuestionList;
import com.samir.utils.PromptUtil;
import com.samir.utils.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewQuestionServiceImpl implements InterviewQuestionService {

    private final ChatGPTApiService chatGPTApiService;

    private final DatabaseService databaseService;

    @Override
    public List<InterviewQuestion> createInterviewQuestions(InterviewQuestionCreationRequest interviewQuestionCreationRequest,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            String errorMessage = Validation.extractErrorMessage(bindingResult);
            throw new ApiException(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage);
        }
        String prompt = PromptUtil.createInterviewQuesPrompt(interviewQuestionCreationRequest);
        ChatGptApiResponse apiResponse = chatGPTApiService.prompt(prompt, 0.7);
        String apiResponseToJSON = StringToJSON.jsonFormatter(apiResponse, interviewQuestionCreationRequest);//improve
        List<InterviewQuestion> interviewQuestionList = JSONtoInterviewQuestionList.interviewQuestionList(apiResponseToJSON);
        return databaseService.save(interviewQuestionList);
    }

    @Override
    public List<InterviewQuestion> getInterviewQuestions() {
        return databaseService.getAllInterviewQuestions();
    }

    @Override
    public List<InterviewQuestion> getRandomInterviewQuestions(InterviewQuestionCreationRequest interviewQuestionCreationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            String errorMessage = Validation.extractErrorMessage(bindingResult);
            throw new ApiException(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage);
        }
        return databaseService.getRandomInterviewQuestions(interviewQuestionCreationRequest);
    }
}
