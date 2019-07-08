package com.greenfoxacademy.ferrilatakryptonitetribesapplication.error;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseModel extends Exception {
  private String status;
  private String message;
  private String path;
  private Timestamp date;

  public ErrorResponseModel() {
  }

  public ErrorResponseModel(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public ErrorResponseModel(String status, String message, String path, Timestamp date) {
    this.status = status;
    this.message = message;
    this.path = path;
    this.date = date;
  }
}
