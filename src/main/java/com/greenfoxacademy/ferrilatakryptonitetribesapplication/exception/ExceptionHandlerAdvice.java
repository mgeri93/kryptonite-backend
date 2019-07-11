package com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.BuildingRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.NotFoundException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.ResourceRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.TimeRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.UnauthorizedRequestException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.UserRelatedException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

  private ErrorResponseModel errorResponseModel;

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponseModel> handleRunTimeException(
      HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(INTERNAL_SERVER_ERROR,
        "Internal server error", httpServletRequest.getServletPath());
    return error(errorResponseModel);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponseModel> handleNotFoundException(
      HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(NOT_FOUND, "Not found exception",
        httpServletRequest.getServletPath());
    return error(errorResponseModel);
  }

  @ExceptionHandler(UserRelatedException.class)
  public ResponseEntity<ErrorResponseModel> handleUserRelatedException(UserRelatedException e,
      HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        e.getMessage(), httpServletRequest.getServletPath());
    return error(errorResponseModel);
  }

  @ExceptionHandler(UnauthorizedRequestException.class)
  public ResponseEntity<ErrorResponseModel> handleUnauthorizedRequestException(
      HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(UNAUTHORIZED,
        "Unauthorized request error", httpServletRequest.getServletPath());
    return error(errorResponseModel);
  }

  @ExceptionHandler(KingdomRelatedException.class)
  public ResponseEntity<ErrorResponseModel> handleKingdomRelatedException(
      HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        "Kingdom-related error", httpServletRequest.getServletPath());
    return error(errorResponseModel);
  }

  @ExceptionHandler(TimeRelatedException.class)
  public ResponseEntity<ErrorResponseModel> handleTimeRelatedException(
      HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        "Timestamp-related error", httpServletRequest.getServletPath());
    return error(errorResponseModel);
  }

  @ExceptionHandler(BuildingRelatedException.class)
  public ResponseEntity<ErrorResponseModel> handleBuildingRelatedException(
      HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        "Building-related error", httpServletRequest.getServletPath());
    return error(errorResponseModel);
  }

  @ExceptionHandler(ResourceRelatedException.class)
  public ResponseEntity<ErrorResponseModel> handleResourceRelatedException(
      HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(BAD_REQUEST, "Resource-related error",
        httpServletRequest.getServletPath());
    return error(errorResponseModel);
  }

  private ResponseEntity<ErrorResponseModel> error(ErrorResponseModel errorResponseModel) {
    return ResponseEntity.status(errorResponseModel.getStatus()).body(errorResponseModel);
  }
}
