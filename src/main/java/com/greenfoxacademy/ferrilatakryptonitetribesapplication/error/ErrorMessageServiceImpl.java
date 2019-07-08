package com.greenfoxacademy.ferrilatakryptonitetribesapplication.error;

import java.sql.Timestamp;
import org.springframework.stereotype.Service;

@Service
public class ErrorMessageServiceImpl implements ErrorMessageService {

  @Override
  public ErrorResponseModel notFound(String path) {
    return new ErrorResponseModel("Error 404", "URL not found, Error 404", path,
        new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public ErrorResponseModel internalServerError(String path) {
    return null;
  }

  @Override
  public ErrorResponseModel unauthorizedRequest(String path) {
    return null;
  }

  @Override
  public ErrorResponseModel forbiddenRequest(String path) {
    return null;
  }

  @Override
  public ErrorResponseModel badRequest(String path) {
    return new ErrorResponseModel("Error 400", "Bad request", path,
        new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public ErrorResponseModel invalidUser(String path) {
    return null;
  }

  @Override
  public ErrorResponseModel invalidTroop(String path) {
    return null;
  }

  @Override
  public ErrorResponseModel invalidKingdom(String path) {
    return null;
  }

  @Override
  public ErrorResponseModel invalidBuilding(String path) {
    return null;
  }

  @Override
  public ErrorResponseModel invalidResource(String path) {
    return null;
  }

  @Override
  public ErrorResponseModel invalidTimeStamp(String path) {
    return new ErrorResponseModel("Error 400, Bad request",
        "Invalid Timestamp values in parameters", path,
        new Timestamp(System.currentTimeMillis()));
  }
}
