package com.epam.starbun.todolist.resource;

import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.service.UserService;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserResource {

  @Autowired
  private final UserService userService;

  @GetMapping("/user/{id}")
  public UserDto getById(@PathVariable("id") String userId) {
    return userService.findById(userId);
  }

  @GetMapping("/users")
  public List<UserDto> getAll() {
    return userService.findAll();
  }

  @PostMapping("/user")
  public UUID addUser(@RequestBody UserDto userDto, HttpServletResponse response) {
    UUID savedUserId = userService.save(userDto);
    if (savedUserId != null) {
      response.setStatus(HttpServletResponse.SC_OK);
      return savedUserId;
    } else {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return null;
    }
  }
}
