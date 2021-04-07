package com.epam.starbun.todolist.resource.impl;

import com.epam.starbun.todolist.annotation.CheckLogin;
import com.epam.starbun.todolist.domain.UserEntity;
import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.resource.UserResource;
import com.epam.starbun.todolist.service.UserService;
import java.time.OffsetDateTime;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.validation.Validator;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserResourceImpl implements UserResource {

  private final UserService userService;

  private final MapperFacade mapperFacade;
  private final Validator validator;

  @Override
  @CheckLogin
  @GetMapping("/{id}")
  public UserDto getById(@PathVariable("id") Long userId) {
    return mapperFacade.map(userService.findById(userId), UserDto.class);
  }

  @Override
  @CheckLogin
  @GetMapping("/")
  public List<UserDto> getUsers(@CookieValue(name = "authUser", required = false) Cookie authCookie) {
    return mapperFacade.mapAsList(userService.findAll(), UserDto.class);
  }

  @Override
  @CheckLogin
  @PostMapping("/")
  public UserDto addUser(@RequestBody @Validated UserDto user, @CookieValue(name = "authUser", required = false) Cookie authCookie) {
    UserEntity userEntity = mapperFacade.map(user, UserEntity.class);
    userEntity.setCreationDate(OffsetDateTime.now());
    return mapperFacade.map(userService.save(userEntity), UserDto.class);
  }

  @Override
  @CheckLogin
  @PutMapping("/")
  public UserDto updateUser(@Validated UserDto user, @CookieValue(name = "authUser", required = false) Cookie authCookie) {
    UserEntity userEntity = mapperFacade.map(user, UserEntity.class);
    return mapperFacade.map(userService.update(userEntity), UserDto.class);
  }

  @Override
  @CheckLogin
  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable("id") Long id, @CookieValue(name = "authUser", required = false) Cookie authCookie) {
    userService.deleteUserById(id);
  }
}
