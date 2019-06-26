package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.User.User;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.Resource.Resource;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Kingdom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @NotNull
  private String owner;
  private String name = owner + "'s kingdom";


  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "resource_id")
  private List<Resource> resourceList = new ArrayList<>();

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

  public Kingdom(@NotNull String owner, String name) {
    this.owner = owner;
    this.name = name;
  }

  public Kingdom() {
  }
}
