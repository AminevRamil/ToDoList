package com.epam.starbun.todolist.service;

import com.epam.starbun.todolist.dto.UserDto;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

  List<UserDto> findAll();

  UUID save(UserDto userDto);

  List<UserDto> findByNickname(String searchName);

  UserDto findById(String id);
}
