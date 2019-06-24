package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingdomService {

  @Autowired
  private IKingdomRepository iKingdomRepository;

  public KingdomService() {
  }

  public boolean isValidKingdom(Kingdom kingdom) {
    if (kingdom.getName() == null || kingdom.getName().equals("")) {
      return false;
    } else {
      return !iKingdomRepository.existsByName(kingdom.getName());
    }
  }
}
