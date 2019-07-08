package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import java.util.List;

public interface PurchaseService {

  Boolean isGoldEnough(Gold gold, Long cost);

  Gold getGoldOfKingdom(List<Resource> kingdomResource);

  int purchaseIfEnoughGold(Gold gold, Long upgradeLevelTo, Long upgradeCost) throws Exception;

  int purchaseTroop(Kingdom kingdom) throws Exception;

  int purchaseBuilding(Kingdom kingdom) throws Exception;

  int purchaseTroopUpgrade(Kingdom kingdom, Long troopId, Long upgradeLevelTo) throws Exception;

  int purchaseBuildingUpgrade(Kingdom kingdom, Long buildingId, Long upgradeLevelTo)
      throws Exception;
}
