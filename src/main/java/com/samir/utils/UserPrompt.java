package com.samir.utils;

import com.samir.dto.UserRequest;

public class UserPrompt {
    public static String promptBuilder(UserRequest userRequest){
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("Generate ").append(userRequest.getNumberOfQuestions()).append(" multiple-choice ")
                .append(userRequest.getRealm()).append(" interview questions for a developer with ")
                .append(userRequest.getFromTargetExp()).append("-").append(userRequest.getToTargetExp())
                .append(" years of experience with difficulty level ").append(userRequest.getFromDifficulty())
                .append("-").append(userRequest.getToDifficulty()).append(" on a scale of 0-10. Include all topics of Object Oriented Programming, Data Structure and Algorithms, and ")
                .append(userRequest.getRealm()).append(" Language. Here is an example format for each question:\n")
                .append("[\n")
                .append("  {\n")
                .append("    \"question\": \"Question here with sometimes code snippets.\\n   a. Option A\\n   b. Option B\\n   c. Option C\\n   d. Option D\",\n")
                .append("    \"answer\": \"a, b, c, or d\"\n")
                .append("  }\n")
                .append("]");

        String prompt = promptBuilder.toString();
        System.out.println("Input Prompt: " + prompt);
        return prompt;
    }
}
