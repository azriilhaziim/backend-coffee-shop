package com.accenture.ws.exception;

import lombok.Getter;
import java.util.Map;

@Getter
public class BadRequestException extends RuntimeException {

    private final int errorId;
    private final String errorMessage;
    private final Map<String, Object> errorDetails;

    public BadRequestException(
            int errorId,
            String errorMessage,
            Map<String, Object> errorDetails
    ) {
        super(errorMessage);
        this.errorId = errorId;
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }
}