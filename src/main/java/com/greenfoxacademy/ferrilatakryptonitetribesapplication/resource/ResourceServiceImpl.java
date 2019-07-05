package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.KingdomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

  private KingdomServiceImpl kingdomService;

  @Autowired
  public ResourceServiceImpl(KingdomServiceImpl kingdomService) {
    this.kingdomService = kingdomService;
  }

  @Override
  public boolean notNullKingdom(Resource resource) {
    return (resource.getKingdom() != null
        && kingdomService.isExistingKingdom(resource.getKingdom()));
  }

  @Override
  public boolean amountSpecified(Resource resource) {
    return (resource.getAmount() != 0);
  }

}
