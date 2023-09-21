package com.samir.service;

import com.samir.dto.ChatGPTApiRequest;
import com.samir.dto.ChatGptApiResponse;
import com.samir.exception.ApiException;
import com.samir.utils.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ChatGPTApiServiceImpl implements ChatGPTApiService {

    @Value("${model}")
    private String model;

    @Value("${apiURL}")
    private String apiURL;

    @Value("${temperature}")
    private Double temperature;

    private final RestTemplate template;

    @Override
    public ChatGptApiResponse response(String prompt) throws ApiException {
        System.out.println("model " + model + " " + "apiURL " + apiURL + " " + "temperature " + temperature);
        try {
            ChatGPTApiRequest apiRequest = new ChatGPTApiRequest(model, prompt, temperature);
            return template.postForObject(apiURL, apiRequest, ChatGptApiResponse.class);
        } catch (HttpClientErrorException e) {
            HttpStatus statusCode = (HttpStatus) e.getStatusCode();
            String desc = Validation.errorMessage(e.getResponseBodyAsString());
            String errorMessage;
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
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "The url at https://api.openai.com/v1/chat/completions is unresponsive ");
        }
    }
}
