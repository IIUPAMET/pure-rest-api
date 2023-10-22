package com.example.purerestapi.controller;

import com.example.purerestapi.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(value = {UserNotFoundException.class})
  protected ResponseEntity<Object> handleConflict(RuntimeException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }
}
