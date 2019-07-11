package com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApplicationUserController {
  private ApplicationUserServiceImpl userService;

  @Autowired
  public ApplicationUserController(ApplicationUserServiceImpl userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody ApplicationUserDTO applicationUserDTO) {
    return userService.registerNewUser(applicationUserDTO);
  }
}
