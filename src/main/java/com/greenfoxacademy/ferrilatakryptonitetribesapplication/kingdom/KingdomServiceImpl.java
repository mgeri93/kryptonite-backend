package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingdomServiceImpl implements KingdomService {

  private IKingdomRepository kingdomRepository;

  @Autowired
  public KingdomServiceImpl(IKingdomRepository kingdomRepository) {
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
  public Kingdom findKingdomById(long id) {
    return kingdomRepository.findKingdomById(id);
  }

  @Override
  public List<Troop> getTroopsOfKingdomById(long kingdomId) {
    Kingdom kingdom = findKingdomById(kingdomId);

    if (kingdomRepository.existsById(kingdomId) && !(kingdom.getTroops().isEmpty())) {
      return kingdom.getTroops();
    } else if (kingdomRepository.existsById(kingdomId) && kingdom.getTroops().isEmpty()) {
      throw new KingdomRelatedException("There are no troops in this kingdom");
    } else {
      throw new KingdomRelatedException("Kingdom ID not found: " + kingdomId);
    }
  }
}
