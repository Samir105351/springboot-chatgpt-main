package com.samir.service;

import com.samir.dto.UserRequest;
import com.samir.entity.InterviewQuestion;

import java.util.List;

public interface PromptService {
    List<InterviewQuestion> UserPrompt(UserRequest userRequest);
}
