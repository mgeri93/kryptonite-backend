package com.greenfoxacademy.springwebapp.Repositories;

import com.greenfoxacademy.springwebapp.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
