package com.samir.service;

import com.samir.dto.ChatGptApiResponse;
import com.samir.exception.ApiException;

public interface ChatGPTApiService {
    ChatGptApiResponse response(String prompt) throws ApiException;
}
