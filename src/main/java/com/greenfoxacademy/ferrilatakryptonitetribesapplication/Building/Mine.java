package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Building;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mine extends Building {

  public Mine() {
    this.setBuildingType("Mine");
  }
}
