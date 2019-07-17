package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import java.util.List;

public interface KingdomService {

  boolean isExistingKingdom(Kingdom kingdom);

  boolean isValidKingdom(Kingdom kingdom);

  Kingdom findKingdomById(long id);

  boolean existById(long id);

  List<Troop> getTroopsOfKingdomById(long id);
}
