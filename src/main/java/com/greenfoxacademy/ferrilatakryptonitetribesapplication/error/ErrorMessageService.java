package com.greenfoxacademy.ferrilatakryptonitetribesapplication.error;

public interface ErrorMessageService {
  ErrorResponseModel notFound(String path);
  ErrorResponseModel internalServerError(String path);
  ErrorResponseModel unauthorizedRequest(String path);
  ErrorResponseModel forbiddenRequest(String path);
  ErrorResponseModel badRequest(String path);
  ErrorResponseModel invalidUser(String path);
  ErrorResponseModel invalidTroop(String path);
  ErrorResponseModel invalidKingdom(String path);
  ErrorResponseModel invalidBuilding(String path);
  ErrorResponseModel invalidResource(String path);
  ErrorResponseModel invalidTimeStamp(String path);
}
