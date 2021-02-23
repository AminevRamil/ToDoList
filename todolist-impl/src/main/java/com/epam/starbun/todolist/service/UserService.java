package com.epam.starbun.todolist.service;

import com.epam.starbun.todolist.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

  boolean save(UserDto userDto);
}
