package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import java.util.List;
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
  private  ResourceServiceImpl resourceService;

  @Autowired
  public KingdomController(ResourceServiceImpl resourceService, KingdomServiceImpl kingdomService) {
    this.resourceService = resourceService;
    this.kingdomService = kingdomService;
  }

  @GetMapping({"/", ""})
  ResponseEntity<String> kingdom() {
    return new ResponseEntity<>("kingdom", HttpStatus.OK);
  }

  @GetMapping("/troops/{kingdomId}")
  List<Troop> getTroopsOfKingdom(@PathVariable(name = "kingdomId") long kingdomId) {
    return kingdomService.getTroopsOfKingdomById(kingdomId);
  }
}
