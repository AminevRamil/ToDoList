package com.epam.starbun.todolist.resource.impl;

import com.epam.starbun.todolist.annotation.CheckLogin;
import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.resource.UserResource;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.validation.Validator;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserResourceImpl implements UserResource {

  private final UserService userService;

  private final Validator validator;

  @Override
  @CheckLogin
  @GetMapping("/{id}")
  public UserDto getById(@PathVariable("id") Long userId) {
    return userService.findById(userId);
  }

  @Override
  @CheckLogin
  @GetMapping("/")
  public List<UserDto> getUsers(@CookieValue(name = "authUser", required = false) Cookie authCookie) {
    return userService.findAll();
  }

  @Override
  @CheckLogin
  @PostMapping("/")
  public UserDto addUser(@RequestBody @Validated UserDto user, @CookieValue(name = "authUser", required = false) Cookie authCookie) {
    return userService.save(user);
  }

  @Override
  @CheckLogin
  @PutMapping("/")
  public UserDto updateUser(@Validated UserDto user, @CookieValue(name = "authUser", required = false) Cookie authCookie) {
    return userService.update(user);
  }

  @Override
  @CheckLogin
  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable("id") Long id, @CookieValue(name = "authUser", required = false) Cookie authCookie) {
    userService.deleteUserById(id);
  }
}
