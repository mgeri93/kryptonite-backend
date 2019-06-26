package com.greenfoxacademy.ferrilatakryptonitetribesapplication.building;

public interface BuildingService {

  boolean isValidBuilding(Building building);

  Building saveBuilding(Building building);

  Building findById(long id);
}
