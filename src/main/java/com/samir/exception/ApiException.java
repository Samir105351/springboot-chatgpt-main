package com.samir.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiException extends RuntimeException {
    private int errorCode;
    private String errorDetails;
    private String description;

    public ApiException(int errorCode, String errorDetails) {
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
    }

    public ApiException(int errorCode, String errorDetails, String description) {
        this(errorCode, errorDetails);
        this.description = description;
    }

}
