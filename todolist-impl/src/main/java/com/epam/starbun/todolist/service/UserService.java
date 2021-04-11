package com.epam.starbun.todolist.service;

import com.epam.starbun.todolist.domain.UserEntity;
import com.epam.starbun.todolist.dto.AuthRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

  List<UserEntity> findAll();

  UserEntity save(UserEntity user);

  UserEntity findById(Long id);

  UserEntity findByNickname(String nickname);

  void deleteUserById(Long userId);

  UserEntity update(UserEntity user);

  UserEntity authorizeUser(AuthRequest authData);
}
