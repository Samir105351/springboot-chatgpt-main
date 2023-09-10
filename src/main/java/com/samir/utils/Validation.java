package com.samir.utils;

import com.samir.dto.InterviewQuestionCreationRequest;
import com.samir.exception.ApiException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Set;

public class Validation {
    public static void validateInterviewQuestionCreationRequest(InterviewQuestionCreationRequest request) {
        ValidatorFactory factory = jakarta.validation.Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<InterviewQuestionCreationRequest>> violations = validator.validate(request);

        if (!violations.isEmpty()) {
            String errorMessage = extractErrorMessage(violations);
            throw new ApiException(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage);
        }
    }
    public static String extractErrorMessage(Set<ConstraintViolation<InterviewQuestionCreationRequest>> violations) {
        StringBuilder errorMessageBuilder = new StringBuilder();

        for (ConstraintViolation<InterviewQuestionCreationRequest> violation : violations) {
            if (errorMessageBuilder.length() > 0) {
                errorMessageBuilder.append("; ");
            }
            errorMessageBuilder.append(violation.getPropertyPath())
                    .append(' ')
                    .append(violation.getMessage());
        }

        return errorMessageBuilder.toString();
    }

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
