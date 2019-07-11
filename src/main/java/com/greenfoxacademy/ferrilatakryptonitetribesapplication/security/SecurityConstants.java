package com.greenfoxacademy.ferrilatakryptonitetribesapplication.security;

public class SecurityConstants {

  public static final long EXPIRATION_TIME = 600_000; //10 min
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String REGISTER_URL = "/register";
}
