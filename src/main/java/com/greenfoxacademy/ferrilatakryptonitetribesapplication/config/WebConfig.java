package com.greenfoxacademy.ferrilatakryptonitetribesapplication.config;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.logging.LoggingInterceptor;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private LoggingInterceptor loggingInterceptor;

  private List<String> excludedEndpoints = Arrays.asList("/register", "/login");

  @Autowired
  public WebConfig(LoggingInterceptor loggingInterceptor) {
    this.loggingInterceptor = loggingInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loggingInterceptor).addPathPatterns("/kingdom");
    registry.addInterceptor(loggingInterceptor).excludePathPatterns(excludedEndpoints);
  }
}
