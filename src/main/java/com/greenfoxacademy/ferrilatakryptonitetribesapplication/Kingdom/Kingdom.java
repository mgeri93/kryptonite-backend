package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.User.User;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Kingdom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @NotNull
  private String owner;
  private String name = owner + "'s kingdom";

  @OneToOne()
  @JoinColumn(name = "user_id")
  private List<User> users;

  public Kingdom(String owner, String name) {
    this.owner = owner;
    this.name = name;
  }

  public Kingdom() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
