package com.samir.service;

import com.samir.dto.ChatGptApiResponse;
import com.samir.dto.InterviewQuestionCreationRequest;

public interface ChatGPTApiService {
    ChatGptApiResponse prompt(String prompt, Double temperature);
}
