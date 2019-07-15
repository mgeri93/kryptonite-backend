package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import java.util.List;
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

  public KingdomController(KingdomServiceImpl kingdomService) {
    this.kingdomService = kingdomService;
  }

  @GetMapping("")
  ResponseEntity<String> kingdom() {
    return new ResponseEntity<>("kingdom", HttpStatus.OK);
  }

  @GetMapping("/{id}/resources")
  List<Resource> getKingdomResources(@PathVariable(name = "id") long id) {
    return kingdomService.listKingdomsResources(id);
  }
}
