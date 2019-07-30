package com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase.PurchaseServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TroopServiceImp implements TroopService {

  private TroopRepository troopRepository;
  private PurchaseServiceImpl purchaseService;

  @Autowired
  public TroopServiceImp(TroopRepository troopRepository, PurchaseServiceImpl purchaseService) {
    this.troopRepository = troopRepository;
    this.purchaseService = purchaseService;
  }

  @Override
  public boolean isValidTroop(Troop troop) {
    return (troop.getLevel() > 0 && troop.getAttack() > 0 && troop.getDefense() > 0
        && troop.getHp() > 0 && troop.getKingdom() != null);
  }

  @Override
  public Troop createTroop(Kingdom kingdom) {
    Troop newTroop = new Troop();
    newTroop.setKingdom(kingdom);
    kingdom.getResourceList().get(1)
        .setAmountPerMinute(kingdom.getResourceList().get(1).getAmountPerMinute() - 1);
    newTroop.setKingdom(kingdom);
    return newTroop;
  }

  public Troop findTroopById(long id) {
    return troopRepository.findTroopById(id);
  }

  public Troop findTroopByLevel(long level) {
    return  troopRepository.findTroopByLevel(level);
  }

  public Troop putTroop(long level, Kingdom kingdom) throws Exception {
    if (kingdom.getTroops().contains(findTroopByLevel(level)) &&
        purchaseService.isGoldEnough((Gold)kingdom.getResourceList().get(0),
        (findTroopByLevel(level).getLevel() + 1) * purchaseService.getTroopCreateCost())) {
      return findTroopByLevel(kingdom.getTroops().get(findTroopByLevel(level).getKingdom().getTroops().))/*contains(findTroopByLevel(level))*/;
    } else {
      throw new Exception("Upgrade is not allowed");
    }
  }

}
