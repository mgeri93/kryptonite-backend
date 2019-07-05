package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import com.google.common.collect.Iterables;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.TroopServiceImp;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

  private BuildingServiceImpl buildingService;
  private TroopServiceImp troopService;
  private ResourceServiceImpl resourceService;

  private Long troopCreateCost = 10L;
  private Long buildingCreateCost = 100L;

  @Autowired
  public PurchaseServiceImpl(
      BuildingServiceImpl buildingService,
      TroopServiceImp troopService,
      ResourceServiceImpl resourceService) {
    this.buildingService = buildingService;
    this.troopService = troopService;
    this.resourceService = resourceService;
  }

  @Override
  public int purchaseBuilding(Kingdom kingdom) {
    List<Resource> kingdomResources = kingdom.getResourceList();
    Gold gold = getGoldOfKingdom(kingdomResources);
    purchaseIfEnoughGold(gold, 1L, buildingCreateCost);
    return gold.getAmount();
  }

  @Override
  public int purchaseBuildingUpgrade(Kingdom kingdom, Long buildingId, Long upgradeLevelTo) {
    Building building = buildingService.findBuildingById(buildingId);
    building.setLevel(upgradeLevelTo);
    List<Resource> kingdomResource = kingdom.getResourceList();
    Gold gold = getGoldOfKingdom(kingdomResource);
    Long buildingUpgradeCost = upgradeLevelTo * buildingCreateCost;
    return purchaseIfEnoughGold(gold, upgradeLevelTo, buildingUpgradeCost);
  }

  @Override
  public int purchaseTroop(Kingdom kingdom) {
    List<Resource> kingdomResources = kingdom.getResourceList();
    Gold gold = getGoldOfKingdom(kingdomResources);
    purchaseIfEnoughGold(gold, 1L, troopCreateCost);
    return gold.getAmount();
  }

  @Override
  public int purchaseTroopUpgrade(Kingdom kingdom, Long troopId, Long upgradeLevelTo) {
    Troop troop = troopService.findTroopById(troopId);
    troop.setLevel(upgradeLevelTo);
    List<Resource> kingdomResource = kingdom.getResourceList();
    Gold gold = getGoldOfKingdom(kingdomResource);
    Long troopUpgradeCost = upgradeLevelTo * troopCreateCost;
    return purchaseIfEnoughGold(gold, upgradeLevelTo, troopUpgradeCost);
  }

  @Override
  public Boolean isGoldEnough(Gold gold, Long cost) {
    return gold.getAmount() >= cost;
  }

  @Override
  public Gold getGoldOfKingdom(List<Resource> kingdomResources) {
    List<Resource> filteredResources =
        kingdomResources.stream().filter(g -> g instanceof Gold).collect(Collectors.toList());
    return (Gold) Iterables.getOnlyElement(filteredResources);
  }

  @Override
  public int purchaseIfEnoughGold(Gold gold, Long upgradeLevelTo, Long upgradeCost) {
    if (isGoldEnough(gold, upgradeCost)) {
      long newGoldAmount = gold.getAmount() - (upgradeLevelTo * upgradeCost);
      gold.setAmount((int) newGoldAmount);
      resourceService.saveResource(gold);
      return gold.getAmount();
    } else {
      return gold.getAmount();
    }
  }
}
