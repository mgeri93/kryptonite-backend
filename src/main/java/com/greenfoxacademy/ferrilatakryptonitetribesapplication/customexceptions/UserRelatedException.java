package com.greenfoxacademy.ferrilatakryptonitetribesapplication.customexceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Setter
@Getter
public class UserRelatedException extends RuntimeException {

  private String path;

  public UserRelatedException(String message, String path) {
    super(message);
    this.path = path;
  }
}
