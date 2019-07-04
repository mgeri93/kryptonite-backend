package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.KingdomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

  private KingdomServiceImpl kingdomService;
  private IResourceRepository resourceRepository;

  @Autowired
  public ResourceServiceImpl(
      KingdomServiceImpl kingdomService, IResourceRepository resourceRepository) {
    this.kingdomService = kingdomService;
    this.resourceRepository = resourceRepository;
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
  public Resource saveResource(Resource resource) {
    return ();
  }
}
