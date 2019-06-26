package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import java.util.List;

public interface IUserService {

  List<User> findAll();
  void saveUser(User user);
}
