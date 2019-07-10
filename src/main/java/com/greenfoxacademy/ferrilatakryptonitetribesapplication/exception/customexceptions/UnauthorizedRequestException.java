package com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedRequestException extends RuntimeException {
  private String path;

  public UnauthorizedRequestException(String message, String path) {
    super(message);
    this.path = path;
  }
}
