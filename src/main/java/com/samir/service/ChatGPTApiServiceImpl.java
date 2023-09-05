package com.samir.service;

import com.samir.dto.ChatGPTApiRequest;
import com.samir.dto.ChatGptApiResponse;
import com.samir.exception.ApiException;
import com.samir.utils.StringToJSON;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ChatGPTApiServiceImpl implements ChatGPTApiService {

    private final RestTemplate template;

    private final String model = "gpt-3.5-turbo-16k";

    private final String apiURL = "https://api.openai.com/v1/chat/completions";

    @Override
    public ChatGptApiResponse prompt(String prompt, Double temperature) throws ApiException {
        ChatGPTApiRequest apiRequest = new ChatGPTApiRequest(model, prompt, temperature);

        try {
            return template.postForObject(apiURL, apiRequest, ChatGptApiResponse.class);
        } catch (HttpClientErrorException e) {
            HttpStatus statusCode = (HttpStatus) e.getStatusCode();
            String desc = StringToJSON.jsonFormatter(e.getResponseBodyAsString());
            String errorMessage = null;

            switch (statusCode) {
                case UNAUTHORIZED:
                    errorMessage = "Invalid Authentication";
                    break;
                case TOO_MANY_REQUESTS:
                    errorMessage = "Too Many Requests";
                    break;
                case INTERNAL_SERVER_ERROR:
                    errorMessage = "Internal Server Error";
                    break;
                case SERVICE_UNAVAILABLE:
                    errorMessage = "Service Unavailable";
                    break;
                default:
                    errorMessage = "There was an unexpected Error";
                    break;
            }
            throw new ApiException(statusCode.value(), errorMessage, desc);
        }
    }
}
