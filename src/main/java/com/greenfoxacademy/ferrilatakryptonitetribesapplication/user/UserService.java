package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;


import org.springframework.http.ResponseEntity;

public interface UserService {
  boolean credentialsProvided(String username, String password);
  boolean validCredentials(String username, String password);
  Object loginResponse (String username, String password);
}
