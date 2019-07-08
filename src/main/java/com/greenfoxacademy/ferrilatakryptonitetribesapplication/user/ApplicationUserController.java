package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/users")
@RestController
public class ApplicationUserController {

  private ApplicationUserServiceImpl userService;

  @Autowired
  public ApplicationUserController(ApplicationUserServiceImpl userService) {
    this.userService = userService;
  }

  /*@PostMapping("/login")
  ResponseEntity login(@RequestBody ApplicationUserDTO applicationUserDTO) {
    if (applicationUserDTO != null) {
      try {
        return userService.loginResponse(applicationUserDTO.getUsername(), applicationUserDTO.getPassword());
      } catch (Exception e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
      }
    }
    return new ResponseEntity(HttpStatus.BAD_REQUEST);
  }*/

  @PostMapping("/register")
  ResponseEntity register(@RequestBody ApplicationUserDTO applicationUserDTO) {
    return userService.registerNewUser(applicationUserDTO);
  }
}
