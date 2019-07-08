package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.ApplicationUser;
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

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
      mappedBy = "kingdom",fetch = FetchType.EAGER)
  private List<Resource> resourceList;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private ApplicationUser applicationUser;

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
      mappedBy = "kingdom", fetch = FetchType.LAZY)
  private List<Building> buildings;

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
      mappedBy = "kingdom", fetch = FetchType.LAZY)
  private List<Troop> troops;

  public Kingdom(String name, ApplicationUser applicationUser) {
    if (name == null || name.equals("")) {
      this.name = applicationUser.getUsername() + "'s kingdom";
    } else {
      this.name = name;
    }
    this.resourceList = new ArrayList<>();
    this.buildings = new ArrayList<>();
    this.troops = new ArrayList<>();
  }

  public Kingdom() {
    this.resourceList = new ArrayList<>();
    this.buildings = new ArrayList<>();
    this.troops = new ArrayList<>();
  }

}
