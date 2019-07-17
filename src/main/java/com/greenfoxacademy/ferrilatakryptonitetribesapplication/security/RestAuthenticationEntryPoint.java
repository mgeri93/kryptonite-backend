package com.greenfoxacademy.ferrilatakryptonitetribesapplication.security;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.ErrorResponseModel;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.ApiResponse;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.UnauthorizedRequestException;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      AuthenticationException e) throws IOException, ServletException {
    ErrorResponseModel errorResponseModel = new ErrorResponseModel(
        UNAUTHORIZED, e.getMessage(), httpServletRequest.getServletPath()
        );
    System.out.println("UNAUTHORIZED");

//    UnauthorizedRequestException response = new UnauthorizedRequestException("Unauthorised");
    OutputStream out = httpServletResponse.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(out, errorResponseModel);
    out.flush();
  }
}
