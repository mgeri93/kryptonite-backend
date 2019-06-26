package com.greenfoxacademy.ferrilatakryptonitetribesapplication.User;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IUserService {

  List<User> findAll();
  void saveUser(User user);

}
