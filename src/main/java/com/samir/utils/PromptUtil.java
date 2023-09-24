package com.samir.utils;

import com.samir.dto.QuestionCreationRequest;

public class PromptUtil {
    public static String createQuesPrompt(QuestionCreationRequest questionCreationRequest) {
        StringBuilder promptBuilder = new StringBuilder();

        promptBuilder.append("Generate ")
                .append(questionCreationRequest.getNumberOfQuestions())
                .append(" ")
                .append(questionCreationRequest.getQuestionType())
                .append(" interview questions on the subject of ")
                .append(questionCreationRequest.getRealm())
                .append(" for a ")
                .append(questionCreationRequest.getProfession())
                .append(" having ")
                .append(questionCreationRequest.getFromTargetExp())
                .append(" to ")
                .append(questionCreationRequest.getToTargetExp())
                .append(" years of experience with difficulty level ")
                .append(questionCreationRequest.getFromDifficulty())
                .append(" to ")
                .append(questionCreationRequest.getToDifficulty())
                .append(" on a scale of 1-10.")
                .append(" Here is an example format:")
                .append("[{")
                .append("\"question\": \"Question here\",")
                .append("\"difficulty\": \"Integer value\",");

                if (questionCreationRequest.getQuestionType().equalsIgnoreCase("multiple-choice")) {
                    promptBuilder.append("\"options\": \"Options here. Example. \na. Option 1 \nb. Option 2 \nc. Option 3 \nd. Option 4\",");
                }

                promptBuilder.append("\"answer\": \"Answer here\"")
                .append("}]");

                String prompt = promptBuilder.toString();
                System.out.println("Input Prompt: " + prompt);
                return prompt;
    }
}
