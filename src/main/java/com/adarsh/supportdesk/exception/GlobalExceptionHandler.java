package com.adarsh.supportdesk.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

import com.adarsh.supportdesk.dto.ValidationErrorResponse;
import com.adarsh.supportdesk.dto.ErrorResponse;
import com.adarsh.supportdesk.exception.TicketNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleValidationException(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return new ValidationErrorResponse(
                "Validation failed",
                errors
        );
    }
  @ExceptionHandler(TicketNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleTicketNotFound(TicketNotFoundException exception)
  {
        return new ErrorResponse(exception.getMessage());
  }
  
}
