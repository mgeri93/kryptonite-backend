package com.greenfoxacademy.ferrilatakryptonitetribesapplication.error;

public interface ErrorResponseService {
  ErrorResponseModel notFound(String path);
  ErrorResponseModel unauthorizedRequest(String path);
  ErrorResponseModel badRequest(String path);
  ErrorResponseModel noSuchUser(String path);
  ErrorResponseModel alreadyExistingUser(String path);
  ErrorResponseModel invalidRegInputs(String path);
  ErrorResponseModel wrongCredentials(String path);
  ErrorResponseModel invalidTroop(String path);
  ErrorResponseModel invalidKingdom(String path);
  ErrorResponseModel invalidBuilding(String path);
  ErrorResponseModel invalidResource(String path);
  ErrorResponseModel invalidTimeStamp(String path);
}
