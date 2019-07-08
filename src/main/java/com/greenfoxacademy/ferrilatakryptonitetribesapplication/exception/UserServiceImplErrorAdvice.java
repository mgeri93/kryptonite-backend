package com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception;

import java.sql.Timestamp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;

import static antlr.Utils.error;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
@Slf4j
public class UserServiceImplErrorAdvice {

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
    return error(INTERNAL_SERVER_ERROR, e);
  }

  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
    return error(NOT_FOUND, e);
  }

  @ExceptionHandler({NoSuchUserException.class})
  public ResponseEntity<String> handleNoSuchUserException(NoSuchUserException e) {
    return error(BAD_REQUEST, e);
  }

  @ExceptionHandler({WrongPasswordException.class})
  public ResponseEntity<String> handleWrongPasswordException(WrongPasswordException e) {
    return error(UNAUTHORIZED, e);
  }

  @ExceptionHandler({AlreadyExistingUserException.class})
  public ResponseEntity<String> handleAlreadyExistingUserException(AlreadyExistingUserException e) {
    return error(BAD_REQUEST, e);
  }

  @ExceptionHandler({UnauthorizedRequestException.class})
  public ResponseEntity<String> handleUnauthorizedRequestException(UnauthorizedRequestException e) {
    return error(UNAUTHORIZED, e);
  }

  @ExceptionHandler({InvalidKingdomException.class})
  public ResponseEntity<String> handleInvalidKingdomException(InvalidKingdomException e) {
    return error(BAD_REQUEST, e);
  }

  @ExceptionHandler({InvalidTimeStampException.class})
  public ResponseEntity<String> handleInvalidTimeStampException(InvalidTimeStampException e) {
    return error(BAD_REQUEST, e);
  }

  @ExceptionHandler({InvalidBuildingException.class})
  public ResponseEntity<String> handleInvalidBuildingException(InvalidBuildingException e) {
    return error(BAD_REQUEST, e);
  }

  @ExceptionHandler({InvalidResourceException.class})
  public ResponseEntity<String> handleInvalidResourceException(InvalidResourceException e) {
    return error(BAD_REQUEST, e);
  }

  private ResponseEntity<String> error(HttpStatus status, Exception e) {
    log.error("Exception : ", e);
    return ResponseEntity.status(status).body(e.getMessage());
  }
}
