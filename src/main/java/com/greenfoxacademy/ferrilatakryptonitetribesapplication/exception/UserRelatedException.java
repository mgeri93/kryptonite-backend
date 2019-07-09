package com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserRelatedException extends RuntimeException{

  public UserRelatedException(String message) {
    super(message);
  }
}
