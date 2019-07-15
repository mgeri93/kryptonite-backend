package com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  private long level;
  private long hp;
  private long attack;
  private long defense;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kingdom_id")
  private Kingdom kingdom;


  public Troop() {
    this.setLevel(1);
    this.setHp(1);
    this.setAttack(1);
    this.setDefense(1);
  }

  public Troop(int level, int hp, int attack, int defense) {
    this.level = Math.max(level, 1);
    this.hp = Math.max(hp, 1);
    this.attack = Math.max(attack, 1);
    this.defense = Math.max(defense, 1);
  }
}
