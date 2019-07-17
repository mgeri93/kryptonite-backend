package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.ApplicationUserServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
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

  private ResourceServiceImpl resourceService;
  private KingdomServiceImpl kingdomService;
  private ApplicationUserServiceImpl applicationUserService;

  @Autowired
  public KingdomController(ResourceServiceImpl resourceService, KingdomServiceImpl kingdomService,
      ApplicationUserServiceImpl applicationUserService) {
    this.resourceService = resourceService;
    this.kingdomService = kingdomService;
    this.applicationUserService = applicationUserService;
  }

  @GetMapping({"/", ""})
  ResponseEntity<String> kingdom() {
    return new ResponseEntity<>("kingdom", HttpStatus.OK);
  }

  @GetMapping("/{id}/resources")
  List<Resource> getKingdomResources(@PathVariable(name = "id") long id) {
    return kingdomService.listKingdomsResources(id);
  }

  @GetMapping("/{id}")
  List<Kingdom> getKingdomById(@PathVariable(name = "id") long id) {
    return applicationUserService.getKingdomListByUserId(id);
  }

  @GetMapping("/troops/{kingdomId}")
  List<Troop> getTroopsOfKingdom(@PathVariable(name = "kingdomId") long kingdomId) {
    return kingdomService.getTroopsOfKingdomById(kingdomId);

  }
}
