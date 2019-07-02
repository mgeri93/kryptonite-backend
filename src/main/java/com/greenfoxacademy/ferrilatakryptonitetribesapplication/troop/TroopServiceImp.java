package com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop;

import org.springframework.stereotype.Service;

@Service
public class TroopServiceImp implements TroopService {

  @Override
  public boolean isValidTroop(Troop troop) {
    return (troop.getLevel() > 0 && troop.getAttack() > 0 && troop.getDefense() > 0
    && troop.getHp() > 0 && troop.getKingdom() != null);
  }
}
