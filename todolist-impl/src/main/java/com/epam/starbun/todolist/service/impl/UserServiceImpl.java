package com.epam.starbun.todolist.service.impl;

import com.epam.starbun.todolist.converter.UserConverter;
import com.epam.starbun.todolist.domain.User;
import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.repository.UserRepository;
import com.epam.starbun.todolist.service.UserService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public UUID save(UserDto userDto) {
    User user = userConverter.createNewUserEntity(userDto);
    try {
      User savedUser = userRepository.save(user);
      return savedUser.getId();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }

  @Override
  public List<UserDto> findByNickname(String searchName) {
    List<User> users = userRepository.findByNicknameLike(searchName);
    return users.stream().map(userConverter::convert).collect(Collectors.toList());
  }

  @Override
  public UserDto findById(String id) {
    User user = userRepository.getOne(UUID.fromString(id));
    return userConverter.convert(user);
  }
}
