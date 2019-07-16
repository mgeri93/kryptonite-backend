package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import org.springframework.beans.factory.annotation.Autowired;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kingdom")
public class KingdomController {

  private ResourceServiceImpl resourceService;
  private KingdomServiceImpl kingdomService;

  @Autowired
  public KingdomController(ResourceServiceImpl resourceService, KingdomServiceImpl kingdomService) {
    this.resourceService = resourceService;
    this.kingdomService = kingdomService;
  }

  @GetMapping({"/", ""})
  ResponseEntity<String> kingdom() {
    return new ResponseEntity<>("kingdom", HttpStatus.OK);
  }

  @GetMapping("/buildings/{kingdomId}")
  ResponseEntity buildingsOfKingdom(@PathVariable(name = "kingdomId") long kingdomId) {
    return kingdomService.getBuildingsOfKingdom(kingdomId);
  }
}
