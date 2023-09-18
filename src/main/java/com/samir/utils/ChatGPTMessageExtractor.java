package com.samir.utils;

import com.samir.dto.ChatGptApiResponse;
import com.samir.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ChatGPTMessageExtractor {
    public static String getMessage(ChatGptApiResponse response) {
        try {
            String message = response.getChoices().get(0).getMessage().getContent();
            if (message.isBlank()) {
                throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "ChatGPT returned a blank response");
            }
            return message;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "ChatGPT is not responding");
        }
    }
}
