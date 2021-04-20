package com.epam.starbun.todolist.service.impl;

import com.epam.starbun.todolist.domain.UserEntity;
import com.epam.starbun.todolist.dto.AuthRequest;
import com.epam.starbun.todolist.exception.RequestException;
import com.epam.starbun.todolist.repository.UserRepository;
import com.epam.starbun.todolist.service.UserService;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public List<UserEntity> findAll() {
    return userRepository.findAll();
  }

  @Override
  public UserEntity save(UserEntity user) {
    user.setCreationDate(OffsetDateTime.now());
    return userRepository.save(user);
  }

  @Override
  public UserEntity findById(Long id) {
    return userRepository.getOne(id);
  }

  @Override
  public UserEntity findByNickname(String nickname) {
    return userRepository.findByNickname(nickname);
  }

  @Override
  public void deleteUserById(Long userId) {
    userRepository.deleteById(userId);
  }

  @Override
  public UserEntity update(UserEntity user) {
    UserEntity userEntity = userRepository.findById(user.getId()).get();
    userEntity.setEmail(user.getEmail());
    userEntity.setPassword(user.getPassword());
    userEntity.setNickname(user.getNickname());
    userRepository.saveAndFlush(userEntity);
    return null;
  }

  @Override
  public UserEntity authorizeUser(AuthRequest authData) {
    UserEntity user = findByNickname(authData.getLogin());
    if (user == null || !user.getPassword().equals(authData.getPassword())) {
      throw new RequestException("Неверный логин или пароль");
    }
    return user;
  }
}
