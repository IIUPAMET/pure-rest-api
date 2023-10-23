package com.example.purerestapi.exception;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException() {
    super("User Not Found");
  }
}
