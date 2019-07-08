package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApplicationUserController {
  private BCryptPasswordEncoder passwordEncoder;
  private ApplicationUserServiceImpl userService;

  @Autowired
  public ApplicationUserController(ApplicationUserServiceImpl userService,
      BCryptPasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  /*@PostMapping("/login")
  ResponseEntity login(@RequestBody ApplicationUserDTO applicationUserDTO) {
    if (applicationUserDTO != null) {
      try {
        return userService.loginResponse(applicationUserDTO.getUsername(),
        applicationUserDTO.getPassword());
      } catch (Exception e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
      }
    }
    return new ResponseEntity(HttpStatus.BAD_REQUEST);
  }*/

  @PostMapping("/register")
  ResponseEntity register(@RequestBody ApplicationUserDTO applicationUserDTO) {
    applicationUserDTO.setPassword(passwordEncoder.encode(applicationUserDTO.getPassword()));
    return userService.registerNewUser(applicationUserDTO);
  }
}
