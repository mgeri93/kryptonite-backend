package com.greenfoxacademy.ferrilatakryptonitetribesapplication.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {

  private static Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HttpServletRequest requestCacheWrapperObject = new ContentCachingRequestWrapper(request);
    requestCacheWrapperObject.getParameterMap();

    log.info("Request URI : {}", request.getRequestURI());
    log.info("Request method: {}", request.getMethod());
    return true;
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    log.info("Response : {}", response.getStatus());
  }
}
