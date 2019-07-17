package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Food extends Resource {

  public Food(int amount, Kingdom kingdom) {
    super(amount, kingdom);
  }

  public Food(int amount) {
    super(amount);
  }

  public Food() {
    this.setAmountPerMinute(10);
  }
}
