package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingdomService {

  @Autowired
  private IKingdomRepository iKingdomRepository;

  public KingdomService() {
  }

  public boolean isExistingKingdom(Kingdom kingdom) {
    return iKingdomRepository.existsByName(kingdom.getName());
  }

  public boolean isValidKingdom(Kingdom kingdom) {
    return (kingdom.getName() != null && !kingdom.getName().equals(""));
  }

}

