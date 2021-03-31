package com.epam.starbun.todolist.resource;

import com.epam.starbun.todolist.annotation.CheckLogin;
import com.epam.starbun.todolist.dto.User;
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
public class UserResource {

  private final UserService userService;

  private final Validator validator;

  @CheckLogin
  @GetMapping("/{id}")
  public User getById(@PathVariable("id") Long userId) {
    return userService.findById(userId);
  }

  @CheckLogin
  @GetMapping("/")
  public List<User> getUsers(@CookieValue(name = "authUser", required = false) Cookie authCookie) {
    return userService.findAll();
  }

  @CheckLogin
  @PostMapping("/")
  public User addUser(@RequestBody @Validated User user, @CookieValue(name = "authUser", required = false) Cookie authCookie) {
    return userService.save(user);
  }

  @CheckLogin
  @PutMapping("/")
  public User updateUser(@Validated User user, @CookieValue(name = "authUser", required = false) Cookie authCookie) {
    return userService.update(user);
  }

  @CheckLogin
  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable("id") Long id, @CookieValue(name = "authUser", required = false) Cookie authCookie) {
    userService.deleteUserById(id);
  }
}
