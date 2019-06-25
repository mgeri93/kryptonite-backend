package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {

  private BuildingRepository buildingRepository;

  @Autowired
  public BuildingService(BuildingRepository buildingRepository) {
    this.buildingRepository = buildingRepository;
  }

  public boolean isValidBuilding(Building building) {
    return building != null;
  }
}
