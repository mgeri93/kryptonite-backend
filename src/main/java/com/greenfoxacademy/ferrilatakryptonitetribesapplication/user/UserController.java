package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.UserRelatedException;
import com.sun.net.httpserver.HttpContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private UserServiceImpl userService;

  @Autowired
  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  ResponseEntity login(@RequestBody UserDTO userDTO, HttpServletRequest httpServletRequest) {
    if (userDTO != null) {
      return userService.loginResponse(userDTO.getUsername(), userDTO.getPassword(),
          httpServletRequest.getServletPath());
    }
    throw new UserRelatedException("Invalid user details!", "/login");
  }

  @PostMapping("/register")
  ResponseEntity register(@RequestBody UserDTO userDTO, HttpServletRequest httpServletRequest) {
    return userService.registerNewUser(userDTO, httpServletRequest.getServletPath());
  }
}
