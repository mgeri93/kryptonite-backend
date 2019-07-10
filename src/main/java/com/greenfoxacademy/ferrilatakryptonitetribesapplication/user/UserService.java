package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import java.util.List;
import org.springframework.http.ResponseEntity;

public interface UserService {

  List<User> findAllUser();

  void saveUser(User user);

  boolean credentialsProvided(String username, String password);

  boolean validCredentials(String username, String password);

  ResponseEntity loginResponse(String username, String password, String path);

  ResponseEntity loginResponseWithValidCredentials(String username, String password, String path);

}
