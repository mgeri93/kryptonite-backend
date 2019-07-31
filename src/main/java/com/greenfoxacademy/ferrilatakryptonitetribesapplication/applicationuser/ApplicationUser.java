package com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ApplicationUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String username;
  private String password;

  @JsonBackReference
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationUser", fetch = FetchType.LAZY)
  private List<Kingdom> kingdoms;

  public ApplicationUser() {

  }

  public ApplicationUser(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
