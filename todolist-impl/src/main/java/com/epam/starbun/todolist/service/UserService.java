package com.epam.starbun.todolist.service;

import com.epam.starbun.todolist.dto.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

  List<User> findAll();

  User save(User user);

  List<User> findByNickname(String searchName);

  User findById(Long id);

  User findOneByNickname(String nickname);

  void deleteUserById(Long userId);

  User update(User user);
}
