package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingdomServiceImpl implements KingdomService {

  private IKingdomRepository kingdomRepository;

  @Autowired
  public KingdomServiceImpl(
      IKingdomRepository kingdomRepository) {
    this.kingdomRepository = kingdomRepository;
  }

  @Override
  public boolean isExistingKingdom(Kingdom kingdom) {
    return kingdomRepository.existsByName(kingdom.getName());
  }

  @Override
  public boolean isValidKingdom(Kingdom kingdom) {
    return (kingdom.getName() != null && !kingdom.getName().equals(""));
  }

  @Override
  public void updateResource(Kingdom kingdom) {

  }
}
