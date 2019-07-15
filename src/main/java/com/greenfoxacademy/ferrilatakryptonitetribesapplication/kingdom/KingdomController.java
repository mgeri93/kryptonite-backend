package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kingdom")
public class KingdomController {

  private ResourceServiceImpl resourceService;
  private KingdomServiceImpl kingdomService;

  public KingdomController(ResourceServiceImpl resourceService, KingdomServiceImpl kingdomService) {
    this.resourceService = resourceService;
    this.kingdomService = kingdomService;
  }

  @GetMapping("")
  ResponseEntity<String> kingdom() throws Exception {
    resourceService.refresh(kingdomService.findKingdomById(1).getResourceList().get(0));
    return new ResponseEntity<>("kingdom", HttpStatus.OK);
  }
}
