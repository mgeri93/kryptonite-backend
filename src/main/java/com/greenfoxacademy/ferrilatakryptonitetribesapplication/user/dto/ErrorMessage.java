package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {

  private String status;
  private String message;

  public ErrorMessage() {}

  public ErrorMessage(String message) {
    status = "exception";
    this.message = message;
  }
}
