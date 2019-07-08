package com.greenfoxacademy.ferrilatakryptonitetribesapplication.error;

import java.sql.Timestamp;
import org.springframework.stereotype.Service;

@Service
public class ErrorResponseServiceImpl implements ErrorResponseService {

  @Override
  public ErrorResponseModel notFound(String path) {
    return new ErrorResponseModel("Error 401, NOT FOUND", "Page not found",
        path, new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public ErrorResponseModel unauthorizedRequest(String path) {
    return new ErrorResponseModel("Error 401, unauthorized", "Unauthorized request",
        path, new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public ErrorResponseModel badRequest(String path) {
    return new ErrorResponseModel("Error 404, Bad request", "Bad request",
        path, new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public ErrorResponseModel noSuchUser(String path) {
    return new ErrorResponseModel("Error 404, Bad request", "No such user in database",
        "/login", new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public ErrorResponseModel alreadyExistingUser(String path) {
    return new ErrorResponseModel("Error 404, Bad request", "User already exists!",
        "/register", new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public ErrorResponseModel invalidRegInputs(String path) {
    return new ErrorResponseModel("Error 404, Bad request", "Invalid username and/or"
        + "password!",
        "/register", new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public ErrorResponseModel wrongCredentials(String path) {
    return new ErrorResponseModel("Error 401, Unauthorized", "Wrong username+password",
        "/login", new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public ErrorResponseModel invalidTroop(String path) {
    return new ErrorResponseModel("Error 404, Bad request", "Troop creation failed",
        path, new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public ErrorResponseModel invalidKingdom(String path) {
    return new ErrorResponseModel("Error 404, Bad request", "Invalid kingdom details",
        path, new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public ErrorResponseModel invalidBuilding(String path) {
    return new ErrorResponseModel("Error 404, Bad request", "Building creation failed",
        path, new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public ErrorResponseModel invalidResource(String path) {
    return new ErrorResponseModel("Error 404, Bad request", "Resource can't be added",
        path, new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public ErrorResponseModel invalidTimeStamp(String path) {
    return new ErrorResponseModel("Error 404, Bad request",
        "Invalid start-finish times", path, new Timestamp(System.currentTimeMillis()));
  }
}
