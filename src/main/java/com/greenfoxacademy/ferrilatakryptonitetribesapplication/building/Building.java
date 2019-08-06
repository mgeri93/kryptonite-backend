package com.greenfoxacademy.ferrilatakryptonitetribesapplication.building;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import javax.persistence.CascadeType;
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
  private double hp;
  private BuildingType buildingType;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "kingdom_id", referencedColumnName = "id")
  @JsonBackReference
  private Kingdom kingdom;
}
