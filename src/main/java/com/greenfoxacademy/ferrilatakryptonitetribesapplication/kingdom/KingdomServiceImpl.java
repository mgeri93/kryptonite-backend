package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase.PurchaseServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.time.TimeServiceImp;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingdomServiceImpl implements KingdomService {

  private IKingdomRepository kingdomRepository;
  private ResourceServiceImpl resourceService;
  private TimeServiceImp timeService;
  private PurchaseServiceImpl purchaseService;

  @Autowired
  public KingdomServiceImpl(IKingdomRepository kingdomRepository, TimeServiceImp timeService) {
    this.kingdomRepository = kingdomRepository;
    this.timeService = timeService;
  }

  public void setServices(
      ResourceServiceImpl resourceService, PurchaseServiceImpl purchaseService) {
    this.resourceService = resourceService;
    this.purchaseService = purchaseService;
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
    return kingdomRepository.findById(id).orElse(null);
  }

  @Override
  public List<Troop> getTroopsOfKingdomById(long kingdomId) {
    Kingdom kingdom = findKingdomById(kingdomId);

    if ((kingdom.getId() == kingdomId) && !(kingdom.getTroops().isEmpty())) {
      return kingdom.getTroops();
    } else if (kingdom.getId() == kingdomId && kingdom.getTroops().isEmpty()) {
      throw new KingdomRelatedException("There are no troops in this kingdom");
    }
    throw new KingdomRelatedException("Kingdom ID not found: " + kingdomId);
  }
}
