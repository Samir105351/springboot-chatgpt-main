package com.samir.utils;

import com.samir.dto.InterviewQuestionCreationRequest;

public class PromptUtil {
    public static String createInterviewQuestionPrompt(InterviewQuestionCreationRequest interviewQuestionCreationRequest) {

        StringBuilder promptBuilder = new StringBuilder();

        promptBuilder.append("Generate ").append(interviewQuestionCreationRequest.getNumberOfQuestions()).append(" multiple-choice ")
                .append(interviewQuestionCreationRequest.getRealm()).append(" interview questions for a developer with ")
                .append(interviewQuestionCreationRequest.getFromTargetExp()).append("-").append(interviewQuestionCreationRequest.getToTargetExp())
                .append(" years of experience with difficulty level ").append(interviewQuestionCreationRequest.getFromDifficulty())
                .append("-").append(interviewQuestionCreationRequest.getToDifficulty()).append(" on a scale of 0-10. Include all topics of Object Oriented Programming, Data Structure and Algorithms, and ")
                .append(interviewQuestionCreationRequest.getRealm()).append(" Language. Here is an example format for each question:\n")
                .append("[\n")
                .append("  {\n")
                .append("    \"question\": \"Question here with sometimes code snippets.\\n   a. Option A\\n   b. Option B\\n   c. Option C\\n   d. Option D\",\n")
                .append("    \"answer\": \"a, b, c, or d\"\n")
                .append("  }\n")
                .append("]");

        String prompt = promptBuilder.toString();
        System.out.println("Input Prompt: \n" + prompt);
        return prompt;
    }
}
