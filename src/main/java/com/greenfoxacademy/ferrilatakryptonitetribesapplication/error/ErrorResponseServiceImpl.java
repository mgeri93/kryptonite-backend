package com.greenfoxacademy.ferrilatakryptonitetribesapplication.error;

import java.sql.Timestamp;
import org.springframework.stereotype.Service;

@Service
public class ErrorResponseServiceImpl implements ErrorResponseService {

  @Override
  public ErrorResponseModel notFound(String path) {
    return null;
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
  public ErrorResponseModel badRequest(String path) {
    return null;
  }

  @Override
  public ErrorResponseModel noSuchUser(String path) {
    return null;
  }

  @Override
  public ErrorResponseModel alreadyExistingUser(String path) {
    return new ErrorResponseModel("Error 404, Bad request", "User already exists!",
        path, new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public ErrorResponseModel invalidRegInputs(String path) {
    return null;
  }

  @Override
  public ErrorResponseModel wrongCredentials(String path) {
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
    return new ErrorResponseModel("Error 404, Bad request",
        "Invalid start-finish times", path, new Timestamp(System.currentTimeMillis()));
  }
}
