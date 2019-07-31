package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.ApplicationUserServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase.PurchaseServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kingdom")
public class KingdomController {

  private KingdomServiceImpl kingdomService;
  private ApplicationUserServiceImpl applicationUserService;
  private PurchaseServiceImpl purchaseService;
  private IKingdomRepository kingdomRepository;

  @Autowired
  public KingdomController(KingdomServiceImpl kingdomService,
      ApplicationUserServiceImpl applicationUserService,
      PurchaseServiceImpl purchaseService,
      IKingdomRepository kingdomRepository) {
    this.kingdomService = kingdomService;
    this.applicationUserService = applicationUserService;
    this.purchaseService = purchaseService;
    this.kingdomRepository = kingdomRepository;
  }

  @GetMapping({"/", ""})
  ResponseEntity<String> kingdom() {
    return new ResponseEntity<>("kingdom", HttpStatus.OK);
  }

  @GetMapping("/{id}")
  List<Kingdom> getKingdomById(@PathVariable(name = "id") long id) {
    return applicationUserService.getKingdomListByUserId(id);
  }

  @GetMapping("/{kingdomId}/buildings")
  ResponseEntity buildingsOfKingdom(@PathVariable(name = "kingdomId") long kingdomId) {
    return kingdomService.getBuildingsOfKingdom(kingdomId);
  }

  @GetMapping("/{kingdomId}/troops")
  List<Troop> getTroopsOfKingdom(@PathVariable(name = "kingdomId") long kingdomId) {
    return kingdomService.getTroopsOfKingdomById(kingdomId);
  }

  @GetMapping("/{id}/resources")
  List<Resource> getKingdomResources(@PathVariable(name = "id") long id) {
    return kingdomService.listKingdomsResources(id);
  }

  @PostMapping("/{kingdomId}/troops")
  ResponseEntity addNewTroopToKingdom(@PathVariable(name = "kingdomId") long id) {
    return purchaseService.purchaseTroop(kingdomRepository.findKingdomById(id));
  }
}
