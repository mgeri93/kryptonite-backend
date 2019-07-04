package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import com.google.common.collect.Iterables;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

  private BuildingServiceImpl buildingService;
  private ResourceServiceImpl resourceService;

  private Long troopCreateCost = 10L;
  private Long buildingCreateCost = 100L;

  @Autowired
  public PurchaseServiceImpl(
      BuildingServiceImpl buildingService, ResourceServiceImpl resourceService) {
    this.buildingService = buildingService;
    this.resourceService = resourceService;
  }

  public void purchaseTroop(Kingdom kingdom) {
    List<Resource> kingdomResources = kingdom.getResourceList();
    Gold gold = getGoldOfKingdom(kingdomResources);
  }

  public Boolean isGoldEnough(Gold gold, Long cost) {
    return gold.getAmount() >= cost;
  }

  public Gold getGoldOfKingdom(List<Resource> kingdomResources) {
    List<Resource> filteredResources =
        kingdomResources.stream().filter(g -> g instanceof Gold).collect(Collectors.toList());
    return (Gold) Iterables.getOnlyElement(filteredResources);
  }

  public Resource purchaseIfEnoughGold(Gold gold, Long upgradeLevelTo, Long upgradeCost) {
    if (isGoldEnough(gold, upgradeCost)) {
      long newGoldAmount = gold.getAmount() - upgradeLevelTo * upgradeCost;
      gold.setAmount((int) newGoldAmount);
    }
    return
  }
}
