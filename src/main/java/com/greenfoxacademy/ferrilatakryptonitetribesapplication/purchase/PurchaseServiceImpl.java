package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingType;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.TownHall;
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
  public int purchaseBuilding(Kingdom kingdom) throws Exception {
    List<Resource> kingdomResources = kingdom.getResourceList();
    Gold gold = getGoldOfKingdom(kingdomResources);
    purchaseIfEnoughGold(gold, 1L, buildingCreateCost);
    return gold.getAmount();
  }

  @Override
  public int purchaseBuildingUpgrade(Kingdom kingdom, Long buildingId, Long upgradeLevelTo)
      throws Exception {
    Building building = buildingService.findBuildingById(buildingId);
    List<Resource> kingdomResource = kingdom.getResourceList();
    Gold gold = getGoldOfKingdom(kingdomResource);
    if (building.getBuildingType() != BuildingType.TownHall) {
      building.setLevel(Math.min(upgradeLevelTo, townHallLevel(kingdom)));
      return purchaseIfEnoughGold(gold, upgradeLevelTo, buildingCreateCost);
    } else if (building.getLevel() < 10L) {
      building.setLevel(upgradeLevelTo);
      return purchaseIfEnoughGold(gold, upgradeLevelTo, buildingCreateCost);
    }
    return gold.getAmount();
  }

  @Override
  public int purchaseTroop(Kingdom kingdom) throws Exception {
    List<Resource> kingdomResources = kingdom.getResourceList();
    Gold gold = getGoldOfKingdom(kingdomResources);
    troopService.createTroop(kingdom);
    purchaseIfEnoughGold(gold, 1L, troopCreateCost);
    return gold.getAmount();
  }

  @Override
  public int purchaseTroopUpgrade(Kingdom kingdom, Long troopId, Long upgradeLevelTo)
      throws Exception {
    Troop troop = troopService.findTroopById(troopId);
    List<Resource> kingdomResource = kingdom.getResourceList();
    Gold gold = getGoldOfKingdom(kingdomResource);
    if (troop.getLevel() < 3) {
      troop.setLevel(upgradeLevelTo);
      return purchaseIfEnoughGold(gold, upgradeLevelTo, troopCreateCost);
    }
    return gold.getAmount();
  }

  @Override
  public Boolean isGoldEnough(Gold gold, Long cost) {
    return gold.getAmount() >= cost;
  }

  @Override
  public Gold getGoldOfKingdom(List<Resource> kingdomResources) {
    List<Resource> filteredResources =
        kingdomResources.stream().filter(g -> g instanceof Gold).collect(Collectors.toList());
    return (Gold) filteredResources.get(0);
  }

  @Override
  public int purchaseIfEnoughGold(Gold gold, Long upgradeLevelTo, Long upgradeCost)
      throws Exception {
    if (isGoldEnough(gold, upgradeCost)) {
      long newGoldAmount = gold.getAmount() - (upgradeLevelTo * upgradeCost);
      gold.setAmount((int) newGoldAmount);
      resourceService.saveResource(gold);
      return gold.getAmount();
    } else {
      throw new Exception("Not enough gold to purchase.");
    }
  }

  public long townHallLevel(Kingdom kingdom) {
    Building townhHall = new TownHall();
    for (Building building : kingdom.getBuildings()) {
      if (building.getBuildingType() == BuildingType.TownHall) {
        townhHall = building;
      }
    }
    return townhHall.getLevel();
  }
}
