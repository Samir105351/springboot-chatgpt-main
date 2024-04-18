package com.samir.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samir.dto.ChatGPTAnswerTemplate;
import com.samir.dto.ChatGptApiResponse;
import com.samir.dto.QuestionCreationRequest;
import com.samir.entity.Question;
import com.samir.utils.JsonParserUtil;
import com.samir.utils.PromptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final DatabaseService databaseService;
    private final ChatGPTApiService chatGPTApiService;
    private final ObjectMapper objectMapper;


    @Override
    public List<Question> createInterviewQuestions(QuestionCreationRequest request) {

        String prompt = PromptUtil.createQuesPrompt(request);

        ChatGptApiResponse chatGPTResponse = chatGPTApiService.response(prompt);
        System.out.println(JsonParserUtil.objectToString(chatGPTResponse, objectMapper));
        List<ChatGPTAnswerTemplate> chatGPTAnswerTemplates = new ArrayList<>();
        try {
            chatGPTAnswerTemplates = Arrays.asList(
                    objectMapper.readValue(
                            chatGPTResponse.getChoices().get(0).getMessage().getContent(),
                            ChatGPTAnswerTemplate[].class)
            );
        } catch (JsonProcessingException ex) {
            System.out.println("ERROR : " + ex.getMessage());
        }

        List<Question> questionList = new ArrayList<>();

        chatGPTAnswerTemplates.forEach(chatGPTAnswerTemplate -> {
            chatGPTAnswerTemplate.setQuestionType(request.getQuestionType());
            chatGPTAnswerTemplate.setProfession(request.getProfession());
            chatGPTAnswerTemplate.setRealm(request.getRealm());
            questionList.add(objectMapper.convertValue(chatGPTAnswerTemplate, Question.class));
        });

        return databaseService.save(questionList);
    }

    @Override
    public List<Question> getQuestions() {
        return databaseService.getAllInterviewQuestions();
    }

}
