package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Gold extends Resource {

  public Gold(int amount, Kingdom kingdom) {
    super(amount, kingdom);
  }

  public Gold(int amount) {
    super(amount);
  }

  public Gold() {
  }
}
