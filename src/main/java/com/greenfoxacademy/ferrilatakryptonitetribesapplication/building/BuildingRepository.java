package com.greenfoxacademy.ferrilatakryptonitetribesapplication.building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
  Building findBuildingById(long id);
}
