package com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class KingdomRelatedException extends RuntimeException {

  public KingdomRelatedException(String message) {
    super(message);
  }
}
