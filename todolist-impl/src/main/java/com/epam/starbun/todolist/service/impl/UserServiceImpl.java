package com.epam.starbun.todolist.service.impl;

import com.epam.starbun.todolist.converter.UserConverter;
import com.epam.starbun.todolist.domain.User;
import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.repository.UserRepository;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserConverter userConverter;

  @Override
  public boolean save(UserDto userDto) {
    User user = userConverter.createNewUserEntity(userDto);
    try {
      userRepository.save(user);
      return true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }
}
