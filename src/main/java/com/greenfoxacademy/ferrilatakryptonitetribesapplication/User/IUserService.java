package com.greenfoxacademy.ferrilatakryptonitetribesapplication.User;

import java.util.List;

public interface IUserService {

  List<User> findAll();
  void saveUser(User user);
}
