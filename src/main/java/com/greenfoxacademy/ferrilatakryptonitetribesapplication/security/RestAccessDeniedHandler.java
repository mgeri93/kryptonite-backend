package com.greenfoxacademy.ferrilatakryptonitetribesapplication.security;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.ErrorResponseModel;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.ApiResponse;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.ForbiddenException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.UserRelatedException;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
    ErrorResponseModel errorResponseModel = new ErrorResponseModel(
        FORBIDDEN, e.getMessage(), httpServletRequest.getServletPath()
    );
    System.out.println("FORBIDDEN");
    OutputStream out = httpServletResponse.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(out, errorResponseModel);
    out.flush();
  }
}
