package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.KingdomServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.time.TimeServiceImp;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

  private KingdomServiceImpl kingdomService;
  private IResourceRepository resourceRepository;
  private TimeServiceImp timeService;

  @Autowired
  public ResourceServiceImpl(KingdomServiceImpl kingdomService,
      IResourceRepository resourceRepository, TimeServiceImp timeService) {
    this.kingdomService = kingdomService;
    this.resourceRepository = resourceRepository;
    this.timeService = timeService;
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
  public void saveResource(Resource resource) {
    resourceRepository.save(resource);
  }

  @Override
  public void refresh(Resource resource) throws Exception {
    Long difference = timeService.timeDifference(resource.getUpdatedAt(),
        new Timestamp(System.currentTimeMillis()));
    if (difference > 0) {
      resource.update((int) (long) difference);
      resourceRepository.save(resource);
    }
  }
}
