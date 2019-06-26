package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Resource;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.KingdomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {


  @Autowired
  KingdomServiceImpl kingdomService;

  @Override
  public boolean notNullKingdom(Resource resource) {
    return (resource.getKingdom() != null &&
        kingdomService.isExistingKingdom(resource.getKingdom()));
  }

  @Override
  public boolean amountSpecified(Resource resource) {
    return (resource.getAmount() != 0);
  }
}
