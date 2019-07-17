package com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ApplicationUserController {

  private BCryptPasswordEncoder encoder;
  private ApplicationUserServiceImpl userService;

  @Autowired
  public ApplicationUserController(ApplicationUserServiceImpl userService,
      BCryptPasswordEncoder encoder) {
    this.userService = userService;
    this.encoder = encoder;
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody ApplicationUserDTO applicationUserDTO) {
    return userService.registerNewUser(applicationUserDTO);
  }

  @PostMapping("/login")
  ResponseEntity login(@RequestBody ApplicationUserDTO applicationUserDTO) {
    return userService
        .loginResponse(applicationUserDTO.getUsername(), applicationUserDTO.getPassword());
  }
}
