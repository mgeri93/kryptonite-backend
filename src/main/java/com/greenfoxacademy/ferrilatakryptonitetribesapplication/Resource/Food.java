package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Resource;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom.Kingdom;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Food extends Resource {

  private String resourceType = "food";

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kingdom_id")
  private Kingdom kingdom;

  public Food(int amount, Kingdom kingdom) {
    super(amount, kingdom);
  }
}
