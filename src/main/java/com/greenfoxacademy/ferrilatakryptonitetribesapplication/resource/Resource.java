package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

@Getter
@Setter
@Entity
public abstract class Resource {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private int amount;
  private Timestamp updatedAt;
  private int amountPerMinute;

  @JsonBackReference
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "kingdom_id")
  private Kingdom kingdom;

  public Resource(int amount, Kingdom kingdom) {
    this.amount = amount;
    this.kingdom = kingdom;
    this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    this.amountPerMinute = 10;
  }

  public Resource(int amount) {
    this.amount = amount;
    this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    this.amountPerMinute = 10;
  }

  public Resource() {
  }

  public void update(int difference) {
    if (difference > 0) {
      setAmount(getAmountPerMinute() * difference
          + getAmount());
      setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    }
  }
}
