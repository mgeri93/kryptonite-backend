package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import java.util.List;

public interface PurchaseService {

  Boolean isGoldEnough(Gold gold, Long cost);

  Gold getGoldOfKingdom(List<Resource> kingdomResource);

  Resource purchaseIfEnoughGold(Gold gold, Long upgradeLevelTo, Long upgradeCost);

  Resource purchaseTroop(Kingdom kingdom);

  Resource purchaseBuilding(Kingdom kingdom);

  Resource purchaseTroopUpgrade(Kingdom kingdom, Long troopId, Long upgradeLevelTo);

  Resource purchaseBuildingUpgrade(Kingdom kingdom, Long buildingId, Long upgradeLevelTo);
}
