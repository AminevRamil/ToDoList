package com.epam.starbun.todolist.service.impl;

import com.epam.starbun.todolist.converter.UserConverter;
import com.epam.starbun.todolist.domain.User;
import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.repository.UserRepository;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private final UserConverter userConverter;

  @Override
  public List<UserDto> findAll() {
    List<User> users = userRepository.findAll();
    List<UserDto> userDtos = users.stream().map(userConverter::convert).collect(Collectors.toList());
    return userDtos;
  }

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

  @Override
  public UserDto findByNickname(String searchName) {
    User user = userRepository.findFirstByNicknameEquals(searchName);
    UserDto userDto = userConverter.convert(user);
    return userDto;
  }
}
