package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase.PurchaseServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.time.TimeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class KingdomServiceImpl implements KingdomService {

  private IKingdomRepository kingdomRepository;
  private ResourceServiceImpl resourceService;
  private TimeServiceImp timeService;
  private PurchaseServiceImpl purchaseService;

  @Autowired
  public KingdomServiceImpl(
      IKingdomRepository kingdomRepository, TimeServiceImp timeService) {
    this.kingdomRepository = kingdomRepository;
    this.timeService = timeService;
  }

  public void setServices(ResourceServiceImpl resourceService,
      PurchaseServiceImpl purchaseService) {
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
  public boolean existById(long id) {
    return kingdomRepository.existsById(id);
  }

  @Override
  public Kingdom findKingdomById(long id) {
    return kingdomRepository.findKingdomById(id);
  }

  public ResponseEntity getBuildingsOfKingdom(long kingdomId) {
    Kingdom kingdom = findKingdomById(kingdomId);
    if (existById(kingdomId) && !kingdom.getBuildings().isEmpty()) {
      return ResponseEntity.status(200).body(kingdom.getBuildings());
    } else if (existById(kingdomId) && kingdom.getBuildings().isEmpty()) {
      throw new KingdomRelatedException("Oops, this kingdom has no buildings. What have you done?");
    }
    throw new KingdomRelatedException("Kingdom ID not found: " + kingdomId);
  }

}
