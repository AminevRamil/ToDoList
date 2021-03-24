package com.epam.starbun.todolist.service.impl;

import com.epam.starbun.todolist.domain.User;
import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.repository.UserRepository;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final MapperFacade mapperFacade;

  @Override
  public List<UserDto> findAll() {
    List<User> users = userRepository.findAll();
    List<UserDto> userDtos = mapperFacade.mapAsList(users, UserDto.class);
    return userDtos;
  }

  @Override
  public UUID save(UserDto userDto) {
    User user = mapperFacade.map(userDto, User.class);
    user.setCreationDate(OffsetDateTime.now());
    user.setId(UUID.randomUUID());
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
    return mapperFacade.mapAsList(users, UserDto.class);
  }

  @Override
  public UserDto findById(String id) {
    User user = userRepository.getOne(UUID.fromString(id));
    return mapperFacade.map(user, UserDto.class);
  }

  @Override
  public UserDto findOneByNickname(String nickname) {
    List<User> userList = userRepository.findByNicknameLike(nickname);
    if (userList.isEmpty()) {
      return null;
    } else {
      return mapperFacade.map(userList.get(0), UserDto.class);
    }
  }
}
