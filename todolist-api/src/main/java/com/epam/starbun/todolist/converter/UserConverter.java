package com.epam.starbun.todolist.converter;

import com.epam.starbun.todolist.domain.User;
import com.epam.starbun.todolist.dto.UserDto;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
public class UserConverter {

  public User createNewUserEntity(UserDto userDto) {
    User user = new User();
    user.setId(UUID.randomUUID());
    user.setNickname(userDto.getNickname());
    user.setEmail(userDto.getEmail());
    user.setPassword(userDto.getPassword());
    user.setCreationDate(OffsetDateTime.now());
    return user;
  }

  public UserDto convert(User user) {
    return new UserDto(user.getNickname(), user.getEmail(), user.getPassword());
  }
}
