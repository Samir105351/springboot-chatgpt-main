package com.samir.service;

import com.samir.dto.ChatGptApiResponse;
import com.samir.exception.ApiException;

public interface ChatGPTApiService {
    ChatGptApiResponse prompt(String prompt, Double temperature) throws ApiException;
}
