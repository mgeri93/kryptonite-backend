package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

public interface KingdomService {

  boolean isExistingKingdom(Kingdom kingdom);

  boolean isValidKingdom(Kingdom kingdom);

  Kingdom findKingdomById(long id);

  boolean existById(long id);

}
