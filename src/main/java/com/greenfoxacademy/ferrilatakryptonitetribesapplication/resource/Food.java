package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Food extends Resource {


  private String resourceType = "food";

  public Food(int amount, Kingdom kingdom) {
    super(amount, kingdom);
  }

  public Food() {
  }
}
