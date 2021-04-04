package com.epam.starbun.todolist.service;

import com.epam.starbun.todolist.dto.AuthRequest;
import com.epam.starbun.todolist.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

  List<User> findAll();

  User save(User user);

  List<User> findByNickname(String searchName);

  User findById(Long id);

  User findOneByNickname(String nickname);

  void deleteUserById(Long userId);

  User update(User user);

  User authorizeUser(AuthRequest authData);
}
