package com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;

public interface TroopService {

  boolean isValidTroop(Troop troop);

  Troop createTroop(Kingdom kingdom);
}
