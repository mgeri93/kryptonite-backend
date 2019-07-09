package com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ErrorResponseModel {

  private HttpStatus status;
  private String message;
  private String path;
  private Timestamp date;

  public ErrorResponseModel(HttpStatus status, String message, String path) {
    this.status = status;
    this.message = message;
    this.path = path;
    this.date = new Timestamp(System.currentTimeMillis());
  }

  public ErrorResponseModel() {
  }
}
