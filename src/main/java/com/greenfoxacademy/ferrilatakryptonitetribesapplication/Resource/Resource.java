package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Resource;


import com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom.Kingdom;
import java.sql.Timestamp;
import java.util.Date;
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
  private String resourceType;
  private int amount;
  private Timestamp updated_at;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kingdom_id")
  private Kingdom kingdom;

  public Resource(int amount, Kingdom kingdom) {
    Date date= new Date();
    long time = date.getTime();
    this.amount = amount;
    this.kingdom = kingdom;
    this.updated_at = new Timestamp(time);
  }

  public Resource() {
  }
}
