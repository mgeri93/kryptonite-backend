package com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Troop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @NotNull
  private int level = 1;
  private int hp = 1;
  private int attack = 1;
  private int defense = 1;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kingdom_id")
  private Kingdom kingdom;

  public Troop() {
    this.kingdom = new Kingdom();
  }

  public Troop(@NotNull int level, int hp, int attack, int defense,
      Kingdom kingdom) {
    if (level > 0) {
      this.level = level;
    }
    if (hp > 0) {
      this.hp = hp;
    }
    if (attack > 0) {
      this.attack = attack;
    }
    if (defense > 0) {
      this.defense = defense;
    }
  }
}
