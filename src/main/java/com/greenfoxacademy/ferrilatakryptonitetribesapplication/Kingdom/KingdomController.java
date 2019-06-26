package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KingdomController {

  @GetMapping("/kingdom")
  ResponseEntity<String> kingdom() {
    return new ResponseEntity<>("kingdom", HttpStatus.OK);
  }
}
