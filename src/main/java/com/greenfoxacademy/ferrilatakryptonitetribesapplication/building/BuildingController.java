package com.greenfoxacademy.ferrilatakryptonitetribesapplication.building;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingController {


  private PurchaseServiceImpl purchaseService;


  @Autowired
  public BuildingController(PurchaseServiceImpl purchaseService) {
    this.purchaseService = purchaseService;
  }

  @PostMapping("/building")
  public String constructBuilding(@RequestBody BuildingDTO buildingDTO) {
    return purchaseService.constructNewBuilding(buildingDTO);
  }
}
