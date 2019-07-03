package com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import java.util.ArrayList;
import java.util.List;
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
  private int level = 0;
  private int hp = 0;
  private int attack = 0;
  private int defense = 0;

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
    List<Integer> validStats = new ArrayList<>();
    validStats.add(Math.max(level, 1));
    validStats.add(Math.max(hp, 1));
    validStats.add(Math.max(attack, 1));
    validStats.add(Math.max(defense, 1));
    this.level = validStats.get(0);
    this.hp = validStats.get(1);
    this.attack = validStats.get(2);
    this.defense = validStats.get(3);
  }
}
