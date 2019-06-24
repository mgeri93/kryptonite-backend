package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Repositories;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
