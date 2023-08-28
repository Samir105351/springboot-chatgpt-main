package com.samir.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.samir.dto.ChatGPTRequest;
import com.samir.dto.ChatGptResponse;
import com.samir.entity.CodingQuestion;
import com.samir.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

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

    @Autowired
    private QuestionService questionService;

    @GetMapping("/coding-questions")
    public List<CodingQuestion> GetAllCodingQuestions(){
        return questionService.getAllCodingQuestions();
    }

    @PostMapping("/create")
    public List<CodingQuestion> CreateQuestionList(@RequestParam("amount") Long amount, @RequestParam("from_target_exp") Long from_target_exp, @RequestParam("to_target_exp") Long to_target_exp, @RequestParam("difficulty") Long difficulty, @RequestParam("realm") String realm) {
        String prompt = "Generate " + amount + " multiple-choice " + realm + " coding question for a developer with " + from_target_exp + "-" + to_target_exp + " years of experience with difficulty level " + difficulty + " on a scale of 1-10 with the answer. " +
                        "Denote strictly as question, codeSnippet (can be empty), options, answer in JSON format. Include all topics regarding Object Oriented Programming, Data Structure and Algorithms and "+realm+" Language. " +
                        "For example, " +
                        "\n[{\n" +
                        "  \"question\": \"Include Questions here\"\n" +
                        "  \"codeSnippet\": \"Include CodeSnippets here\",\n" +
                        "  \"options\": {\n" +
                        "    \"a\": \"Option 1\",\n" +
                        "    \"b\": \"Option 2\",\n" +
                        "    \"c\": \"Option 3\",\n" +
                        "    \"d\": \"Option 4\"\n" +
                        "  },\n" +
                        "  \"answer\": \"c\"\n" +
                        "}\n" +
                        "\n" +
                        "{\n" +
                        "  \"question\": \"Include questions here\",\n" +
                        "  \"codeSnippet\": \"Can be null\",\n" +
                        "  \"options\": {\n" +
                        "    \"a\": \"Option 1\",\n" +
                        "    \"b\": \"Option 2\",\n" +
                        "    \"c\": \"Option 3\",\n" +
                        "    \"d\": \"Option 4\"\n" +
                        "  },\n" +
                        "  \"answer\": \"a\"\n" +
                        "}]";
        System.out.println("Input Prompt: "+prompt);

        ChatGPTRequest request = new ChatGPTRequest(model, prompt, temperature);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);

        // Modify the JSON response string to add missing fields
        String jsonResponse = chatGptResponse.getChoices().get(0).getMessage().getContent();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Parse the JSON response as an array of objects
            JsonNode jsonArray = objectMapper.readTree(jsonResponse);

            // Iterate through the array and add attributes to each object
            for (JsonNode jsonObject : jsonArray) {
                ((ObjectNode) jsonObject).put("realm", realm);
                ((ObjectNode) jsonObject).put("fromTargetExp", from_target_exp);
                ((ObjectNode) jsonObject).put("toTargetExp", to_target_exp);
                ((ObjectNode) jsonObject).put("difficulty", difficulty);
            }

            // Convert the modified array back to a string
            jsonResponse = jsonArray.toString();
        } catch (IOException e) {
            // Handle the exception appropriately
            e.printStackTrace();
        }
        System.out.println("Output Prompt: "+jsonResponse);
        return questionService.save(jsonResponse);
    }
}