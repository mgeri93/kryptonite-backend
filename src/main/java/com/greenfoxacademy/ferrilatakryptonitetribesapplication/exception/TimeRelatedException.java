package com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Setter
@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TimeRelatedException extends RuntimeException {
  private String path;

  public TimeRelatedException(String message, String path) {
    super(message);
    this.path = path;
  }
}
