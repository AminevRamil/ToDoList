package com.epam.starbun.todolist.resource;

import com.epam.starbun.todolist.dto.User;
import com.epam.starbun.todolist.exception.RequestException;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserResource {

  private final UserService userService;

  private final Validator validator;

  @GetMapping("/{id}")
  public User getById(@PathVariable("id") Long userId) {
    return userService.findById(userId); //orElseThrow
  }

  @GetMapping("/")
  public List<User> getUsers() {
    return userService.findAll();
  }

  @PostMapping("/")
  public User addUser(@RequestBody User user) {
    Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
    if (!constraintViolations.isEmpty()) {
      throw new RequestException(constraintViolations.stream().map(ConstraintViolation::getMessage).toArray(String[]::new));
    }
    return userService.save(user);
  }

  @PutMapping("/")
  public User updateUser(@RequestBody User user) {
    return userService.update(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable("id") Long id) {
    userService.deleteUserById(id);
  }
}
