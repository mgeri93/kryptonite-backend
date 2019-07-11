package com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.dto;

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
