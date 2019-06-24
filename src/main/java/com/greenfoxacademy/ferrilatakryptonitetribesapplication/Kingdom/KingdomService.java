package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingdomService {

  @Autowired
  private IKingdomRepository iKingdomRepository;

  public boolean isValidKingdom(Kingdom kingdom) {
    return iKingdomRepository.existsByName(kingdom.getName());
  }
}
