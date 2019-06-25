package com.greenfoxacademy.ferrilatakryptonitetribesapplication.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

   boolean existsByUsername(String name);

}
