package com.samir.service;

import com.samir.dto.UserRequest;

public interface ChatGPTApiService {
    String ChatGPTApiResponse(String prompt, UserRequest userRequest);
}
