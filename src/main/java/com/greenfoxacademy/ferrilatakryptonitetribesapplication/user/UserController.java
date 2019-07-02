package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private UserServiceImp userService;

  @Autowired
  public UserController(UserServiceImp userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  ResponseEntity<String> login() {
    return new ResponseEntity<>("login", HttpStatus.OK);
  }

  @PostMapping("/register")
  ResponseEntity register(@RequestBody UserDTO userDTO) {
    return userService.registerNewUser(userDTO);
  }
}
