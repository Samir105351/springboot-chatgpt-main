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

    @Override
    public ChatGptApiResponse prompt(String prompt, Double temperature) throws ApiException {
        String model = "gpt-3.5-turbo-16k";
        String apiURL = "https://api.openai.com/v1/chat/completions";
        ChatGPTApiRequest apiRequest = new ChatGPTApiRequest(model, prompt, temperature);

        try {
            return template.postForObject(apiURL, apiRequest, ChatGptApiResponse.class);
        } catch (HttpClientErrorException e) {
            HttpStatus statusCode = (HttpStatus) e.getStatusCode();
            String desc = StringToJSON.jsonFormatter(e.getResponseBodyAsString());
            String errorMessage = switch (statusCode) {
                case UNAUTHORIZED -> "Invalid Authentication";
                case TOO_MANY_REQUESTS -> "Too Many Requests";
                case INTERNAL_SERVER_ERROR -> "Internal Server Error";
                case SERVICE_UNAVAILABLE -> "Service Unavailable";
                default -> "There was an unexpected Error";
            };

            throw new ApiException(statusCode.value(), errorMessage, desc);
        } catch (Exception e){
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Internal Server Error","The url at https://api.openai.com/v1/chat/completions is unresponsive ");
        }
    }
}
