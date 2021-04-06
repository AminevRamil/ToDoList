package com.epam.starbun.todolist.resource;

import com.epam.starbun.todolist.dto.UserDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.List;

public interface UserResource {
  @GetMapping("/{id}")
  UserDto getById(@PathVariable("id") Long userId);


  @GetMapping("/")
  List<UserDto> getUsers(@CookieValue(name = "authUser", required = false) Cookie authCookie);

  @PostMapping("/")
  UserDto addUser(@RequestBody @Validated UserDto user, @CookieValue(name = "authUser", required = false) Cookie authCookie);

  @PutMapping("/")
  UserDto updateUser(@Validated UserDto user, @CookieValue(name = "authUser", required = false) Cookie authCookie);

  @DeleteMapping("/{id}")
  void deleteUser(@PathVariable("id") Long id, @CookieValue(name = "authUser", required = false) Cookie authCookie);
}
