package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.ApplicationUserServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase.PurchaseServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.TroopDTO;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.TroopServiceImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kingdom")
public class KingdomController {

  private KingdomServiceImpl kingdomService;
  private ApplicationUserServiceImpl applicationUserService;
  private TroopServiceImp troopService;
  private PurchaseServiceImpl purchaseService;

  @Autowired
  public KingdomController(KingdomServiceImpl kingdomService,
      ApplicationUserServiceImpl applicationUserService,
      TroopServiceImp troopService, PurchaseServiceImpl purchaseService) {
    this.kingdomService = kingdomService;
    this.applicationUserService = applicationUserService;
    this.troopService = troopService;
    this.purchaseService = purchaseService;
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

  @PutMapping("/{kingdomId}/troop/{lvl}")
  public String upgradeTroop(@PathVariable(name = "kingdomId") long kingdomId,
      @PathVariable(name = "lvl") long lvl) throws Exception {
    return purchaseService.purchaseTroopUpgrade(kingdomService.findKingdomById(kingdomId),
        troopService.findTroopByLevel(lvl).getId(), lvl + 1);

  }


}
