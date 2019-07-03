package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

public interface KingdomService {

  boolean isExistingKingdom(Kingdom kingdom);

  boolean isValidKingdom(Kingdom kingdom);

  void updateResource(Kingdom kingdom);

}
