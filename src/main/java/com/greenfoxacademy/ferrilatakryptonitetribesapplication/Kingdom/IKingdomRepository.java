package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom;

import org.springframework.data.repository.CrudRepository;

public interface IKingdomRepository extends CrudRepository<Kingdom, Long> {

  public boolean existsByName(String name);
}
