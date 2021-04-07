package com.epam.starbun.todolist.resource.impl;

import com.epam.starbun.todolist.dto.AuthRequest;
import com.epam.starbun.todolist.dto.RequestResponse;
import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.resource.LoginResource;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class LoginResourceImpl implements LoginResource {

  private final UserService userService;

  private final Validator validator;

  @Override
  @PostMapping("/login")
  public RequestResponse login(@Validated @RequestBody AuthRequest authRequest, HttpServletResponse response) {
    UserDto user = userService.authorizeUser(authRequest);
    Cookie authCookie = new Cookie("authUser", user.getNickname());
    authCookie.setMaxAge(3600);
    authCookie.setPath("/");
    response.addCookie(authCookie);
    return new RequestResponse("Успешно", "Авторизация успешна", "Куки сохранены");
  }

  @Override
  @PostMapping("/logout")
  public RequestResponse logout(HttpServletResponse response,
                                @CookieValue(value = "authUser", required = false) Cookie authUser) {
    authUser.setMaxAge(0);
    response.addCookie(authUser);
    return new RequestResponse("Успешно","Разавторизация");
  }

}