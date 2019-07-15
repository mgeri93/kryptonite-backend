package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class KingdomDTO {

  private long id;
  private String name;
  private List<Building> buildings;
  private List<Troop> troops;
  private List<Resource> resources;

  public KingdomDTO(long id, String name, List<Building> buildings,
      List<Troop> troops, List<Resource> resources) {
    this.id = id;
    this.name = name;
    this.buildings = buildings;
    this.troops = troops;
    this.resources = resources;
  }
}
