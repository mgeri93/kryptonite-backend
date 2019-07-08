package com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTimeStampException extends RuntimeException {

  public InvalidTimeStampException(String message) {
    super(message);
  }
}
