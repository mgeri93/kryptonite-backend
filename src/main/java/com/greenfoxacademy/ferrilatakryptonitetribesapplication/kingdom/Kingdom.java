package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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

  @NotNull private String name;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "resource_id")
  private List<Resource> resourceList;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "kingdom", fetch = FetchType.LAZY)
  private List<Building> buildings;

  public Kingdom(String name, User user) {
    if (name == null || name.equals("")) {
      this.name = user.getUsername() + "'s kingdom";
    } else {
      this.name = name;
    }
    this.resourceList = new ArrayList<>();
  }

  public Kingdom() {}

  public Kingdom(String name) {
    this.name = name;
  }
}
