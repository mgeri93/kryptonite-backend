package com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser;

import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

  ApplicationUser findByUsername(String username);

  boolean existsByUsername(String name);
}
