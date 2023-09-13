package com.samir.utils;


import com.samir.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class Validation {
    public static String extractErrorMessage(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        System.out.println(fieldErrors);
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : fieldErrors) {
            errorMessage.append(error.getField()).append(" ").append(error.getDefaultMessage()).append("; ");
        }
        return errorMessage.toString();
    }

    public static void bindingErrorChecking(BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            String errorMessage = Validation.extractErrorMessage(bindingResult);
            throw new ApiException(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage);
        }
    }
}
