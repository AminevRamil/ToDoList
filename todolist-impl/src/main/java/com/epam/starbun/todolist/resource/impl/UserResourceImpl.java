package com.epam.starbun.todolist.resource.impl;

import com.epam.starbun.todolist.annotation.CheckLogin;
import com.epam.starbun.todolist.domain.UserEntity;
import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.resource.UserResource;
import com.epam.starbun.todolist.service.UserService;
import java.time.OffsetDateTime;
import java.util.List;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserResourceImpl implements UserResource {

  private final UserService userService;

  private final MapperFacade mapperFacade;

  @Override
  @CheckLogin
  public UserDto getById(Long userId) {
    return mapperFacade.map(userService.findById(userId), UserDto.class);
  }

  @Override
  @CheckLogin
  public List<UserDto> getUsers(Cookie authCookie) {
    return mapperFacade.mapAsList(userService.findAll(), UserDto.class);
  }

  @Override
  @CheckLogin
  public UserDto addUser(UserDto user, Cookie authCookie) {
    UserEntity userEntity = mapperFacade.map(user, UserEntity.class);
    userEntity.setCreationDate(OffsetDateTime.now());
    return mapperFacade.map(userService.save(userEntity), UserDto.class);
  }

  @Override
  @CheckLogin
  public UserDto updateUser(UserDto user, Cookie authCookie) {
    UserEntity userEntity = mapperFacade.map(user, UserEntity.class);
    return mapperFacade.map(userService.update(userEntity), UserDto.class);
  }

  @Override
  @CheckLogin
  public void deleteUser(Long id, Cookie authCookie) {
    userService.deleteUserById(id);
  }
}
