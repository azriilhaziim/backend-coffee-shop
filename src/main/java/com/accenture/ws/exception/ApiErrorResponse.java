package com.accenture.ws.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {

    private int errorCode;
    private String errorMessage;
    private Map<String, Object> errorDetails;

}