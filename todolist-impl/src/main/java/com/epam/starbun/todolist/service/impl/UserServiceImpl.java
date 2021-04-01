package com.epam.starbun.todolist.service.impl;

import com.epam.starbun.todolist.domain.UserEntity;
import com.epam.starbun.todolist.dto.AuthRequest;
import com.epam.starbun.todolist.dto.User;
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
  public List<User> findAll() {
    List<UserEntity> userEntities = userRepository.findAll();
    List<User> users = mapperFacade.mapAsList(userEntities, User.class);
    return users;
  }

  @Override
  public User save(User user) {
    UserEntity userEntity = mapperFacade.map(user, UserEntity.class);
    userEntity.setCreationDate(OffsetDateTime.now());
    try {
      UserEntity savedUserEntity = userRepository.save(userEntity);
      return mapperFacade.map(savedUserEntity, User.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }

  @Override
  public List<User> findByNickname(String searchName) {
    List<UserEntity> userEntities = userRepository.findByNicknameLike(searchName);
    return mapperFacade.mapAsList(userEntities, User.class);
  }

  @Override
  public User findById(Long id) {
    UserEntity userEntity = userRepository.getOne(id);
    return mapperFacade.map(userEntity, User.class);
  }

  @Override
  public User findOneByNickname(String nickname) {
    List<UserEntity> userEntityList = userRepository.findByNicknameLike(nickname);
    if (userEntityList.isEmpty()) {
      return null;
    } else {
      return mapperFacade.map(userEntityList.get(0), User.class);
    }
  }

  @Override
  public void deleteUserById(Long userId) {
    userRepository.deleteById(userId);
  }

  @Override
  public User update(User user) {
    UserEntity userEntity = userRepository.findById(user.getId()).get();
    userEntity.setEmail(user.getEmail());
    userEntity.setPassword(user.getPassword());
    userEntity.setNickname(user.getNickname());
    userRepository.saveAndFlush(userEntity);
    return null;
  }

  @Override
  public User authorizeUser(AuthRequest authData) {
    User user = findOneByNickname(authData.getLogin());
    if (user == null || !user.getPassword().equals(authData.getPassword())) {
      throw new RequestException("Неверный логин или пароль");
    }
    return user;
  }
}
