package com.example.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/*
        @RestControllerAdvice - tells Spring to intercept all API exceptions

 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*this method doesn't have to be called anywhere. Spring does the magic and calls the correct method
        within this exception handler
    * */
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound (BookNotFoundException ex){
        ErrorResponse errorResponseObject = new ErrorResponse();
        errorResponseObject.setTimestamp(LocalDateTime.now().toString());
        errorResponseObject.setMessage(ex.getMessage());
        //aici setStatus ia exact valoarea de not found 404
        errorResponseObject.setStatus(HttpStatus.NOT_FOUND.value());

        // aici status da exact statusul HTTP trimis la client
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseObject);
    }

}
