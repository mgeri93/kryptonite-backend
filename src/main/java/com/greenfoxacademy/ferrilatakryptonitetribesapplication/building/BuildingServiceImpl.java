package com.greenfoxacademy.ferrilatakryptonitetribesapplication.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl implements BuildingService {

  private BuildingRepository buildingRepository;

  @Autowired
  public BuildingServiceImpl(BuildingRepository buildingRepository) {
    this.buildingRepository = buildingRepository;
  }

  @Override
  public boolean isValidBuilding(Building building) {
    return (building != null);
  }

  @Override
  public Building saveBuilding(Building building) {
    return buildingRepository.save(building);
  }

  @Override
  public Building findById(long id) {
    return buildingRepository.findById(id);
  }
}
