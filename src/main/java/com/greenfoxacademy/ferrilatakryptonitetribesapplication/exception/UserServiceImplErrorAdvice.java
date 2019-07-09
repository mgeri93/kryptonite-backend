package com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception;

import java.sql.Timestamp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
@Slf4j
public class UserServiceImplErrorAdvice {

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

  @ExceptionHandler({AlreadyExistingUserException.class})
  public ResponseEntity<ErrorResponseModel> handleAlreadyExistingUserException(AlreadyExistingUserException e) {
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

  @ExceptionHandler({InvalidKingdomException.class})
  public ResponseEntity<ErrorResponseModel> handleInvalidKingdomException(InvalidKingdomException e) {
    ErrorResponseModel errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        "Kingdom-related error", "");
    return error(e, errorResponseModel);
  }

  @ExceptionHandler({InvalidTimeStampException.class})
  public ResponseEntity<ErrorResponseModel> handleInvalidTimeStampException(InvalidTimeStampException e) {
    ErrorResponseModel errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        "Timestamp-related error", "");
    return error(e, errorResponseModel);
  }

  @ExceptionHandler({InvalidBuildingException.class})
  public ResponseEntity<ErrorResponseModel> handleInvalidBuildingException(InvalidBuildingException e) {
    ErrorResponseModel errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        "Building-related error", "");
    return error(e, errorResponseModel);
  }

  @ExceptionHandler({InvalidResourceException.class})
  public ResponseEntity<ErrorResponseModel> handleInvalidResourceException(InvalidResourceException e) {
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
