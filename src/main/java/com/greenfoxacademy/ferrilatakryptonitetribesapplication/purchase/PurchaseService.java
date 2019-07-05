package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import java.util.List;

public interface PurchaseService {

  Boolean isGoldEnough(Gold gold, Long cost);

  Gold getGoldOfKingdom(List<Resource> kingdomResource);

  int purchaseIfEnoughGold(Gold gold, Long upgradeLevelTo, Long upgradeCost);

  int purchaseTroop(Kingdom kingdom);

  int purchaseBuilding(Kingdom kingdom);

  int purchaseTroopUpgrade(Kingdom kingdom, Long troopId, Long upgradeLevelTo);

  int purchaseBuildingUpgrade(Kingdom kingdom, Long buildingId, Long upgradeLevelTo);
}
