package com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TroopServiceImp implements TroopService {

  private TroopRepository troopRepository;

  @Autowired
  public TroopServiceImp(TroopRepository troopRepository) {
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
    kingdom.getResourceList().get(1)
        .setAmountPerMinute(kingdom.getResourceList().get(1).getAmountPerMinute() - 1);
    newTroop.setKingdom(kingdom);
    return newTroop;
  }

  public Troop findTroopById(long id) {
    return troopRepository.findTroopById(id);
  }

  public Troop getTroopToUpdate(Kingdom kingdom, long level) {
    List<Troop> sameLevelTroops = kingdom.getTroops()
        .stream()
        .filter(troop -> troop.getLevel() == level)
        .collect(Collectors.toList());
    return sameLevelTroops.get((int)Math.random() * (sameLevelTroops.size() + 1));
  }

}
