package com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidBuildingException extends RuntimeException {

  public InvalidBuildingException(String message) {
    super(message);
  }
}
