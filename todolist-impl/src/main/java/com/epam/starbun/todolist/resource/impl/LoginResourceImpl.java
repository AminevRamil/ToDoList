package com.epam.starbun.todolist.resource.impl;

import com.epam.starbun.todolist.domain.UserEntity;
import com.epam.starbun.todolist.dto.AuthRequest;
import com.epam.starbun.todolist.dto.RequestResponse;
import com.epam.starbun.todolist.resource.LoginResource;
import com.epam.starbun.todolist.service.UserService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginResourceImpl implements LoginResource {

  private final UserService userService;

  @Override
  public RequestResponse login(AuthRequest authRequest, HttpServletResponse response) {
    UserEntity userEntity = userService.authorizeUser(authRequest);
    Cookie authCookie = new Cookie("authUser", userEntity.getNickname());
    authCookie.setMaxAge(3600);
    authCookie.setPath("/");
    response.addCookie(authCookie);
    return new RequestResponse("Успешно", "Авторизация успешна", "Куки сохранены");
  }

  @Override
  public RequestResponse logout(HttpServletResponse response, Cookie authUser) {
    authUser.setMaxAge(0);
    response.addCookie(authUser);
    return new RequestResponse("Успешно", "Разавторизация");
  }
}
