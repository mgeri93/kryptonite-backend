package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KingdomController {

  KingdomRepository kingdomRepository;

  public KingdomController(KingdomRepository kingdomRepository) {
    this.kingdomRepository = kingdomRepository;
  }

  @GetMapping("/kingdom")
  ResponseEntity<String> kingdom(){
    return new ResponseEntity<>("kingdom", HttpStatus.OK);
  }
}
