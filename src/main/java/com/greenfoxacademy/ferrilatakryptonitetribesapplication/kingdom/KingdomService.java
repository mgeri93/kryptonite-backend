package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

public interface KingdomService {

  boolean isExistingKingdom(Kingdom kingdom);

  boolean isValidKingdom(Kingdom kingdom);

  boolean existById(long id);

  Kingdom findKingdomById(long id);

}
