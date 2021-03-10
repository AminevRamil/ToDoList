package com.epam.starbun.todolist.service;

import com.epam.starbun.todolist.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

  List<UserDto> findAll();

  boolean save(UserDto userDto);

  UserDto findByNickname(String searchName);
}
