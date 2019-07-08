package com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop;

import org.springframework.data.repository.CrudRepository;

public interface TroopRepository extends CrudRepository<Troop, Long> {

  Troop findTroopById(long id);
}
