package com.greenfoxacademy.ferrilatakryptonitetribesapplication.building;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingDTO {
  private String type;
  private long level;
  private long kingdomId;

  public BuildingDTO(String type, long level, long kingdomId) {
    this.type = type;
    this.level = level;
    this.kingdomId = kingdomId;
  }

  public BuildingDTO() {
  }
}
