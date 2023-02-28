package com.example.workflowmanagementbackend.exception;

import com.example.workflowmanagementbackend.model.BadRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class ApplicationException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BadRequest handleInvalidRequestException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));
        BadRequest badRequest = new BadRequest(403,errors);
        return badRequest;
    }

    @ExceptionHandler(ExitsException.class)
    public BadRequest handleExitsException(ExitsException ex){
        Map<String,String> errors = new HashMap<>();
        errors.put("message",ex.getMessage());
        BadRequest badRequest = new BadRequest(402,errors);
        return badRequest;
    }


}
