package com.greenfoxacademy.ferrilatakryptonitetribesapplication.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {



  @GetMapping("/login")
  ResponseEntity<String> login() {
    return new ResponseEntity<>("login", HttpStatus.OK);
  }

  @GetMapping("/register")
  ResponseEntity<String> register() {
    return new ResponseEntity<>("register", HttpStatus.OK);
  }
}
