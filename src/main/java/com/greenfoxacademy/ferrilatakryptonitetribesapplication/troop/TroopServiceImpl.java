package com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.ResourceRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TroopServiceImpl implements TroopService {

  private TroopRepository troopRepository;

  @Autowired
  public TroopServiceImpl(TroopRepository troopRepository) {
    this.troopRepository = troopRepository;
  }

  @Override
  public boolean isValidTroop(Troop troop) {
    return (troop.getLevel() > 0 && troop.getAttack() > 0 && troop.getDefense() > 0
        && troop.getHp() > 0 && troop.getKingdom() != null);
  }

  @Override
  public Troop createTroop(Kingdom kingdom) {
    Troop newTroop = new Troop();
    newTroop.setKingdom(kingdom);
    kingdom.getResourceList().get(0)
        .setAmountPerMinute(kingdom.getResourceList().get(0).getAmountPerMinute() - 1);
    newTroop.setKingdom(kingdom);
    troopRepository.save(newTroop);
    return newTroop;
  }

  public Troop findTroopById(long id) {
    return troopRepository.findTroopById(id);
  }

  public Troop getTroopToUpdate(Kingdom kingdom, long level) throws ResourceRelatedException {
    if (troopRepository.existsByLevel(level)) {
      List<Troop> sameLevelTroops = kingdom.getTroops()
          .stream()
          .filter(troop -> troop.getLevel() == level)
          .collect(Collectors.toList());
      return sameLevelTroops.get((int) Math.random() * (sameLevelTroops.size() + 1));
    } else {
      throw new ResourceRelatedException("Upgrade is not successful");
    }
  }



}
