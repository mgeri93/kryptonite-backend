package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kingdom")
public class KingdomController {

  private KingdomServiceImpl kingdomService;

  @Autowired
  public KingdomController(KingdomServiceImpl kingdomService) {
    this.kingdomService = kingdomService;
  }

  @GetMapping("")
  ResponseEntity<String> kingdom() {
    return new ResponseEntity<>("kingdom", HttpStatus.OK);
  }

  @GetMapping("/troops/{kingdomId}")
  ResponseEntity troopsOfKingdom(@PathVariable(name = "kingdomId") long kingdomId) {
    Kingdom kingdom = kingdomService.findKingdomById(kingdomId);

    if (kingdom.getTroops().isEmpty()) {
      throw new KingdomRelatedException("There is no troops in this kingdom");
    } else if (kingdom.getId() == kingdomId) {
      return ResponseEntity.status(200).body(kingdom.getTroops());
    }
    throw new KingdomRelatedException("Kingdom ID not found: " + kingdomId);
  }
}
