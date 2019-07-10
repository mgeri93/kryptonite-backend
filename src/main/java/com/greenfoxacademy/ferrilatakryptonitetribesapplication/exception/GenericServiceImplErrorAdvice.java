package com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.BuildingRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.NotFoundException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.ResourceRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.TimeRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.UnauthorizedRequestException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.UserRelatedException;
import lombok.extern.slf4j.Slf4j;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericServiceImplErrorAdvice {

  ErrorResponseModel errorResponseModel;

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponseModel> handleRunTimeException() {
    errorResponseModel = new ErrorResponseModel(INTERNAL_SERVER_ERROR,
        "Internal server error", "");
    return error(errorResponseModel);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponseModel> handleNotFoundException(NotFoundException e) {
    errorResponseModel = new ErrorResponseModel(NOT_FOUND,
        "Not found exception", e.getPath());
    return error(errorResponseModel);
  }

  @ExceptionHandler(UserRelatedException.class)
  public ResponseEntity<ErrorResponseModel> handleUserRelatedException(UserRelatedException e) {
    errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        e.getMessage(), e.getPath());
    return error(errorResponseModel);
  }

  @ExceptionHandler(UnauthorizedRequestException.class)
  public ResponseEntity<ErrorResponseModel> handleUnauthorizedRequestException(
      UnauthorizedRequestException e) {
    errorResponseModel = new ErrorResponseModel(UNAUTHORIZED,
        "Unauthorized request error", e.getPath());
    return error(errorResponseModel);
  }

  @ExceptionHandler(KingdomRelatedException.class)
  public ResponseEntity<ErrorResponseModel> handleKingdomRelatedException(
      KingdomRelatedException e) {
    errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        "Kingdom-related error", e.getPath());
    return error(errorResponseModel);
  }

  @ExceptionHandler(TimeRelatedException.class)
  public ResponseEntity<ErrorResponseModel> handleTimeRelatedException(TimeRelatedException e) {
    errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        "Timestamp-related error", e.getPath());
    return error(errorResponseModel);
  }

  @ExceptionHandler(BuildingRelatedException.class)
  public ResponseEntity<ErrorResponseModel> handleBuildingRelatedException(
      BuildingRelatedException e) {
    errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        "Building-related error", e.getPath());
    return error(errorResponseModel);
  }

  @ExceptionHandler(ResourceRelatedException.class)
  public ResponseEntity<ErrorResponseModel> handleResourceRelatedException(
      ResourceRelatedException e) {
    errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        e.getMessage(), e.getPath());
    return error(errorResponseModel);
  }

  private ResponseEntity<ErrorResponseModel> error(ErrorResponseModel errorResponseModel) {
    return ResponseEntity.status(errorResponseModel.getStatus()).body(errorResponseModel);
  }
}
