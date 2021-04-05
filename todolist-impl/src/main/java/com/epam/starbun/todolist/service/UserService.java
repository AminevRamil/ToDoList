package com.epam.starbun.todolist.service;

import com.epam.starbun.todolist.dto.AuthRequest;
import com.epam.starbun.todolist.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

  List<UserDto> findAll();

  UserDto save(UserDto user);

  List<UserDto> findByNickname(String searchName);

  UserDto findById(Long id);

  UserDto findOneByNickname(String nickname);

  void deleteUserById(Long userId);

  UserDto update(UserDto user);

  UserDto authorizeUser(AuthRequest authData);
}
