package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class KingdomServiceImpl implements KingdomService {

  private IKingdomRepository kingdomRepository;
  private ResourceServiceImpl resourceService;

  @Autowired
  public KingdomServiceImpl(IKingdomRepository kingdomRepository) {
    this.kingdomRepository = kingdomRepository;
  }

  public void setServices(ResourceServiceImpl resourceService) {
    this.resourceService = resourceService;
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
  public List<Resource> listKingdomsResources(long id) {
    if (kingdomRepository.existsById(id)) {
      return kingdomRepository.findKingdomById(id).getResourceList();
    }
    throw new KingdomRelatedException("No Kingdom exists with this id");
  }

  @Override
  public Kingdom findKingdomById(long id) {
    return kingdomRepository.findKingdomById(id);
  }

  @Override
  public List<Troop> getTroopsOfKingdomById(long kingdomId) {
    Kingdom kingdom = findKingdomById(kingdomId);

    if (kingdomRepository.existsById(kingdomId) && !(kingdom.getTroops().isEmpty())) {
      resourceService.refresh(findKingdomById(kingdomId).getResourceList().get(0));
      return kingdom.getTroops();
    } else if (kingdomRepository.existsById(kingdomId) && kingdom.getTroops().isEmpty()) {
      resourceService.refresh(findKingdomById(kingdomId).getResourceList().get(0));
      throw new KingdomRelatedException("There are no troops in this kingdom");
    } else {
      throw new KingdomRelatedException("Kingdom ID not found: " + kingdomId);
    }
  }

  @Override
  public boolean existById(long id) {
    return kingdomRepository.existsById(id);
  }

  public ResponseEntity getBuildingsOfKingdom(long kingdomId) {
    Kingdom kingdom = findKingdomById(kingdomId);
    if (existById(kingdomId) && !kingdom.getBuildings().isEmpty()) {
      resourceService.refresh(kingdom.getResourceList().get(0));
      return ResponseEntity.status(200).body(kingdom.getBuildings());
    } else if (existById(kingdomId) && kingdom.getBuildings().isEmpty()) {
      resourceService.refresh(kingdom.getResourceList().get(0));
      throw new KingdomRelatedException("Oops, this kingdom has no buildings. What have you done?");
    }
    throw new KingdomRelatedException("Kingdom ID not found: " + kingdomId);
  }
}
