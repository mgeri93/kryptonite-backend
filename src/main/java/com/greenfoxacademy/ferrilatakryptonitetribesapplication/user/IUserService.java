package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import java.util.List;

public interface IUserService {

  List<User> findAllUser();

  void saveUser(User user);
}
