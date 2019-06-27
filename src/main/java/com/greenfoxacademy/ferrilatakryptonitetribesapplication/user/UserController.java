package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @PostMapping("/login")
  ResponseEntity<String> login(@RequestBody User user) {
    return new ResponseEntity<>("login", HttpStatus.OK);
  }

  @PostMapping("/register")
  ResponseEntity<String> register() {
    return new ResponseEntity<>("register", HttpStatus.OK);
  }
}
