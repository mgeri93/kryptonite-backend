package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  UserServiceImpl userService;

  @PostMapping("/login")
  ResponseEntity login(@RequestBody UserDTO userDTO) {
    if (userDTO != null) {
      return userService.loginResponse(userDTO.getUsername(), userDTO.getPassword());
    }
    else return new ResponseEntity(HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/register")
  ResponseEntity<String> register() {
    return new ResponseEntity<>("register", HttpStatus.OK);
  }
}
