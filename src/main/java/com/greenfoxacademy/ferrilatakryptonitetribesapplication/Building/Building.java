package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Building;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public abstract class Building {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long level;
  private double HP;
  private String buildingType;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kingdom_id", referencedColumnName = "id")
  private Kingdom kingdom;
}
