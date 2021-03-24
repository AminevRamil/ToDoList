package com.epam.starbun.todolist.service;

import com.epam.starbun.todolist.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface UserService {

  List<UserDto> findAll();

  UUID save(UserDto userDto);

  List<UserDto> findByNickname(String searchName);

  UserDto findById(String id);

  UserDto findOneByNickname(String nickname);
}
