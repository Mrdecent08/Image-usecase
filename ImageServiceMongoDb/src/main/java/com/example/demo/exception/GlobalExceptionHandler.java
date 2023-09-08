package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ControllerAdvice
public class GlobalExceptionHandler {
 
    @ExceptionHandler(value = ImageNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleException(ImageNotFoundException ex)
    {
        return new ErrorResponse(
            HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}