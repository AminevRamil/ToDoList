package com.epam.starbun.todolist.service.impl;

import com.epam.starbun.todolist.domain.UserEntity;
import com.epam.starbun.todolist.dto.AuthRequest;
import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.exception.RequestException;
import com.epam.starbun.todolist.repository.UserRepository;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final MapperFacade mapperFacade;

  @Override
  public List<UserDto> findAll() {
    List<UserEntity> userEntities = userRepository.findAll();
    List<UserDto> users = mapperFacade.mapAsList(userEntities, UserDto.class);
    return users;
  }

  @Override
  public UserDto save(UserDto user) {
    UserEntity userEntity = mapperFacade.map(user, UserEntity.class);
    userEntity.setCreationDate(OffsetDateTime.now());
    try {
      UserEntity savedUserEntity = userRepository.save(userEntity);
      return mapperFacade.map(savedUserEntity, UserDto.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }

  @Override
  public List<UserDto> findByNickname(String searchName) {
    List<UserEntity> userEntities = userRepository.findByNicknameLikeIgnoreCase(searchName.toLowerCase());
    return mapperFacade.mapAsList(userEntities, UserDto.class);
  }

  @Override
  public UserDto findById(Long id) {
    UserEntity userEntity = userRepository.getOne(id);
    return mapperFacade.map(userEntity, UserDto.class);
  }

  @Override
  public UserDto findOneByNickname(String nickname) {
    List<UserEntity> userEntityList = userRepository.findByNicknameLikeIgnoreCase(nickname.toLowerCase());
    if (userEntityList.isEmpty()) {
      return null;
    } else {
      return mapperFacade.map(userEntityList.get(0), UserDto.class);
    }
  }

  @Override
  public void deleteUserById(Long userId) {
    userRepository.deleteById(userId);
  }

  @Override
  public UserDto update(UserDto user) {
    UserEntity userEntity = userRepository.findById(user.getId()).get();
    userEntity.setEmail(user.getEmail());
    userEntity.setPassword(user.getPassword());
    userEntity.setNickname(user.getNickname());
    userRepository.saveAndFlush(userEntity);
    return null;
  }

  @Override
  public UserDto authorizeUser(AuthRequest authData) {
    UserDto user = findOneByNickname(authData.getLogin());
    if (user == null || !user.getPassword().equals(authData.getPassword())) {
      throw new RequestException("Неверный логин или пароль");
    }
    return user;
  }
}
