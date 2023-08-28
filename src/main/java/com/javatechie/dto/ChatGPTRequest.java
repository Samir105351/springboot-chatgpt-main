package com.javatechie.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTRequest {

    private String model;
    private List<Message> messages;
    private double temperature;

    // Add a constructor with a default temperature value of 0.6
    public ChatGPTRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
        this.temperature = 0.7; // Set the default temperature to 0.6
    }

    // Add another constructor that allows setting a custom temperature
    public ChatGPTRequest(String model, String prompt, Double temperature) {
        this(model, prompt); // Call the default constructor to set default values
        if (temperature != null) {
            this.temperature = temperature; // Set the custom temperature if provided
        }
    }
}
