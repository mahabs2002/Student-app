package com.tech.studentapp.controller;

import com.tech.studentapp.exception.StudentNotFoundException;
import com.tech.studentapp.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse>  handleStudentNotFound(StudentNotFoundException ex){
        ErrorResponse error=new ErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                "STUDENT ID INVALID"
        );
        return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }
}
