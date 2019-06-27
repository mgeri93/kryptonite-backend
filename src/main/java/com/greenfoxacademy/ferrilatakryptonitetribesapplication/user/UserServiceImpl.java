package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public boolean credentialsProvided(String username, String password) {
    return (username != null && !username.equals("") && password != null && !password.equals(""));
  }

  @Override
  public boolean validCredentials(String username, String password) {
    return userRepository.findByUsername().getPassword().equals(password);
  }

  @Override
  public ResponseEntity loginResponse(String username, String password) {

    if (!credentialsProvided(username, password)) {
      String missingCred1 = "";
      String missingCred2 = "";
      if (username.equals("") || username == null) {
        missingCred1 = "username";
      }
      if (password.equals("") || password == null) {
        missingCred2 = "password";
      }
      if (!missingCred1.equals("") && !missingCred2.equals("")) {
        return new ResponseEntity<>("{ \"status\" : \"error\", "
            + "\"message\" : \"Missing parameter(s): username, password!\" }",
            HttpStatus.BAD_REQUEST);
      }
    }
  }
}


