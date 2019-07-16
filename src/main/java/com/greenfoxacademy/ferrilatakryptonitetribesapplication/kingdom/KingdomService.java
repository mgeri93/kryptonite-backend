package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import java.util.List;

public interface KingdomService {

  boolean isExistingKingdom(Kingdom kingdom);

  boolean isValidKingdom(Kingdom kingdom);

  Kingdom findKingdomById(long id);

<<<<<<< HEAD
  List<Troop> getTroopsOfKingdomById(long id);
=======
>>>>>>> development
}
