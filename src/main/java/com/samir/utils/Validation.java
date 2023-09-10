package com.samir.utils;


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
}
