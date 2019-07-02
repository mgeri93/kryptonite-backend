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
  private int hp;
  private int attack;
  private int defense;

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
    this.hp = hp;
    this.attack = attack;
    this.defense = defense;
    this.kingdom = kingdom;
  }
}
