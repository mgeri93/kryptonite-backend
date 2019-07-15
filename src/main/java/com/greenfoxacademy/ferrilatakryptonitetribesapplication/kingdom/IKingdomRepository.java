package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import org.springframework.data.repository.CrudRepository;

public interface IKingdomRepository extends CrudRepository<Kingdom, Long> {

  public boolean existsByName(String name);
  Kingdom findById(long id);
}
