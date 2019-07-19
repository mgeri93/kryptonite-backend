package com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.BuildingRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.NotFoundException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.ResourceRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.TimeRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.UnauthorizedRequestException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.UserRelatedException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

  private ErrorResponseModel errorResponseModel;

  private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponseModel> handleRunTimeException(RuntimeException e,
      HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(INTERNAL_SERVER_ERROR,
        e.getMessage(), httpServletRequest.getServletPath());
    log.error(e.getMessage());
    return createCustomErrorResponse(errorResponseModel);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponseModel> handleNotFoundException(NotFoundException e,
      HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(NOT_FOUND, e.getMessage(),
        httpServletRequest.getServletPath());
    log.error(e.getMessage());
    return createCustomErrorResponse(errorResponseModel);
  }

  @ExceptionHandler(UserRelatedException.class)
  public ResponseEntity<ErrorResponseModel> handleUserRelatedException(UserRelatedException e,
      HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        e.getMessage(), httpServletRequest.getServletPath());
    log.error(e.getMessage());
    return createCustomErrorResponse(errorResponseModel);
  }

  @ExceptionHandler(UnauthorizedRequestException.class)
  public ResponseEntity<ErrorResponseModel> handleUnauthorizedRequestException(
      UnauthorizedRequestException e, HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(UNAUTHORIZED,
        e.getMessage(), httpServletRequest.getServletPath());
    log.error(e.getMessage());
    return createCustomErrorResponse(errorResponseModel);
  }

  @ExceptionHandler(KingdomRelatedException.class)
  public ResponseEntity<ErrorResponseModel> handleKingdomRelatedException(KingdomRelatedException e,
      HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        e.getMessage(), httpServletRequest.getServletPath());
    log.error(e.getMessage());
    return createCustomErrorResponse(errorResponseModel);
  }

  @ExceptionHandler(TimeRelatedException.class)
  public ResponseEntity<ErrorResponseModel> handleTimeRelatedException(TimeRelatedException e,
      HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        e.getMessage(), httpServletRequest.getServletPath());
    log.error(e.getMessage());
    return createCustomErrorResponse(errorResponseModel);
  }

  @ExceptionHandler(BuildingRelatedException.class)
  public ResponseEntity<ErrorResponseModel> handleBuildingRelatedException(
      BuildingRelatedException e,
      HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(BAD_REQUEST,
        e.getMessage(), httpServletRequest.getServletPath());
    log.error(e.getMessage());
    return createCustomErrorResponse(errorResponseModel);
  }

  @ExceptionHandler(ResourceRelatedException.class)
  public ResponseEntity<ErrorResponseModel> handleResourceRelatedException(
      ResourceRelatedException e, HttpServletRequest httpServletRequest) {
    errorResponseModel = new ErrorResponseModel(BAD_REQUEST, e.getMessage(),
        httpServletRequest.getServletPath());
    log.error(e.getMessage());
    return createCustomErrorResponse(errorResponseModel);
  }

  private ResponseEntity<ErrorResponseModel> createCustomErrorResponse(
      ErrorResponseModel errorResponseModel) {
    return ResponseEntity.status(errorResponseModel.getStatus()).body(errorResponseModel);
  }
}
