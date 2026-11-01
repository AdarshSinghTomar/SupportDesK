package com.adarsh.supportdesk.dto;
import java.util.Map;

public record ValidationErrorResponse(
    String message,
    Map<String,String> errors
) {
    
}
