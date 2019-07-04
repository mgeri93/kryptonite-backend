package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.KingdomServiceImpl;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

  @Override
  public long timeDifference(Resource resource) {
    Timestamp resourceTime = resource.getUpdatedAt();
    long now = Timestamp.valueOf(LocalDateTime.now()).getTime();
    return  now - resourceTime.getTime();
  }

}
