package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Resource;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Gold extends Resource {
  private String resourceType = "gold";

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kingdom_id")
  private Kingdom kingdom;

  public Gold(int amount, Kingdom kingdom) {
    super(amount, kingdom);
  }
}
