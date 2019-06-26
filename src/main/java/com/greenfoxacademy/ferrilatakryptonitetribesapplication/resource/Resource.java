package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.sun.tools.javac.code.Attribute.Enum;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public abstract class Resource {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private int amount;
  private Timestamp updatedAt;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kingdom_id")
  private Kingdom kingdom;

  public Resource(int amount, Kingdom kingdom) {
    this.amount = amount;
    this.kingdom = kingdom;
    this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
  }

  public Resource() {
  }
}
