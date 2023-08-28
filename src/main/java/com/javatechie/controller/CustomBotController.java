package com.javatechie.controller;

import com.javatechie.dto.ChatGPTRequest;
import com.javatechie.dto.ChatGptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/bot")
public class CustomBotController {

    @Value("${openai.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;

    @Value("${openai.api.temperature}")
    private Double temperature;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public String chat(
            @RequestParam("amount") Long amount,
            @RequestParam("from_target_exp") Long from_target_exp,
            @RequestParam("to_target_exp") Long to_target_exp,
            @RequestParam("difficulty") Long difficulty
    ) {
        // The maximum time to wait for each ChatGPTResponse (in seconds).
        long timeoutInSeconds = 30;

        String prompt = "Generate a multiple-choice Java coding question for a developer with "
                + from_target_exp + "-" + to_target_exp + " years of experience with difficulty level " + difficulty
                + " with answer. Denote question as Question: and answer as Answer:";

        StringBuilder responses = new StringBuilder();

        // Loop to generate the specified amount of responses.
        for (int i = 0; i < amount; i++) {
            CompletableFuture<String> responseFuture = CompletableFuture.supplyAsync(() -> {
                ChatGPTRequest request = new ChatGPTRequest(model, prompt, temperature);
                ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);
                return chatGptResponse.getChoices().get(0).getMessage().getContent();
            });

            try {
                // Wait for the response with a timeout.
                String response = responseFuture.get(timeoutInSeconds, TimeUnit.SECONDS);
                responses.append(response).append("\n\n"); // Add response to the StringBuilder.
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                // Handle exceptions here (e.g., log, return an error message).
                e.printStackTrace();
            }
        }

        return responses.toString();
    }
}
