package com.greenfoxacademy.ferrilatakryptonitetribesapplication.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.ErrorResponseModel;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest httpServletRequest, HttpServletResponse
      httpServletResponse, AuthenticationException e) throws IOException {
    ErrorResponseModel errorResponseModel = new ErrorResponseModel(
        UNAUTHORIZED, e.getMessage(), httpServletRequest.getServletPath());
    OutputStream out = httpServletResponse.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(out, errorResponseModel);
    out.flush();
  }
}
