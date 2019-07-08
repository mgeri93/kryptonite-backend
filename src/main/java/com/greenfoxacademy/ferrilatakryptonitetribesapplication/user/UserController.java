package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.error.ErrorResponseModel;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.error.ErrorResponseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private UserServiceImpl userService;
  private ErrorResponseServiceImpl errorResponseService;

  @Autowired
  public UserController(UserServiceImpl userService,
      ErrorResponseServiceImpl errorResponseService) {
    this.userService = userService;
    this.errorResponseService = errorResponseService;
  }

  @PostMapping("/login")
  ResponseEntity login(@RequestBody UserDTO userDTO) {
    if (userDTO != null) {
      try {
        return userService.loginResponse(userDTO.getUsername(), userDTO.getPassword());
      } catch (Exception e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
      }
    }
    return new ResponseEntity(HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/register")
  Object register(@RequestBody UserDTO userDTO) throws Exception{
    try {
      return userService.registerNewUser(userDTO);
    } catch (Exception e) {
      return errorResponseService.alreadyExistingUser("/register");
    }
  }
}
