package com.example.loancalculatorapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.rmi.server.ExportException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//exception handling for all controllers in the application
@ControllerAdvice
public class GlobalExceptionHandler {

    //handle specific exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadGatewayException.class)
    public ResponseEntity<?> handleBadGatewayException(BadGatewayException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_GATEWAY);
    }

    //handle global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception,WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //handle custom validation exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions (MethodArgumentNotValidException exception){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),"Validation Error",exception.getFieldError().getDefaultMessage());
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
}
