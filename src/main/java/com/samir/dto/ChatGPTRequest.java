package com.samir.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTRequest {

    private String model;
    private List<Message> messages;
    private double temperature;

    public ChatGPTRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
        this.temperature = 0.7;
    }

    public ChatGPTRequest(String model, String prompt, Double temperature) {
        this(model, prompt);
        if (temperature != null) {
            this.temperature = temperature;
        }
    }
}
