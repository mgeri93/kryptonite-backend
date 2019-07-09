package com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
@Slf4j
public class GenericServiceImplErrorAdvice {

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<ErrorResponseModel> handleRunTimeException(RuntimeException e) {
    ErrorResponseModel errorResponseModel = new ErrorResponseModel(INTERNAL_SERVER_ERROR,
        "Internal server error", "");
    return error(e, errorResponseModel);
  }

  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<ErrorResponseModel> handleNotFoundException(NotFoundException e) {
    ErrorResponseModel errorResponseModel = new ErrorResponseModel(NOT_FOUND,
        "Not found exception", "");
    return error(e, errorResponseModel);
  }

  @ExceptionHandler({UserRelatedException.class})
  public ResponseEntity<ErrorResponseModel> handleAlreadyExistingUserException(
      UserRelatedException e) {
    ErrorResponseModel errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        "User-related error", "");
    return error(e, errorResponseModel);
  }

  @ExceptionHandler({UnauthorizedRequestException.class})
  public ResponseEntity<ErrorResponseModel> handleUnauthorizedRequestException(UnauthorizedRequestException e) {
    ErrorResponseModel errorResponseModel = new ErrorResponseModel(UNAUTHORIZED,
        "Unauthorized request error", "");
    return error(e, errorResponseModel);
  }

  @ExceptionHandler({KingdomRelatedException.class})
  public ResponseEntity<ErrorResponseModel> handleInvalidKingdomException(KingdomRelatedException e) {
    ErrorResponseModel errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        "Kingdom-related error", "");
    return error(e, errorResponseModel);
  }

  @ExceptionHandler({TimeRelatedException.class})
  public ResponseEntity<ErrorResponseModel> handleInvalidTimeStampException(TimeRelatedException e) {
    ErrorResponseModel errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        "Timestamp-related error", "");
    return error(e, errorResponseModel);
  }

  @ExceptionHandler({BuildingRelatedException.class})
  public ResponseEntity<ErrorResponseModel> handleInvalidBuildingException(
      BuildingRelatedException e) {
    ErrorResponseModel errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        "Building-related error", "");
    return error(e, errorResponseModel);
  }

  @ExceptionHandler({ResourceRelatedException.class})
  public ResponseEntity<ErrorResponseModel> handleInvalidResourceException(
      ResourceRelatedException e) {
    ErrorResponseModel errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        "Resource-related error", "");
    return error(e, errorResponseModel);
  }

  private ResponseEntity<ErrorResponseModel> error(Exception e,
      ErrorResponseModel errorResponseModel) {
    log.error("Exception : ", e);
    return ResponseEntity.status(errorResponseModel.getStatus()).body(errorResponseModel);
  }
}
