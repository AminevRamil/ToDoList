package com.epam.starbun.todolist.resource;

import com.epam.starbun.todolist.dto.AuthRequest;
import com.epam.starbun.todolist.dto.AuthResponse;
import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginResource {

  private final UserService userService;

  @PostMapping("login")
  public AuthResponse login(@RequestBody AuthRequest authRequest, HttpServletResponse response,
                            @CookieValue(value = "authUser", required = false) String authUser) {
    if (authUser != null && userService.findOneByNickname(authUser) != null) {
      return new AuthResponse("Вы уже авторизованы. Если хотите сменить пользователя, то необходимо разавторизоваться");
    }
    UserDto user = userService.findOneByNickname(authRequest.getLogin());
    if (user == null && !user.getPassword().equals(authRequest.getPassword())) {
      return new AuthResponse("Неверный логин или пароль");
    }

    Cookie authCookie = new Cookie("authUser", user.getNickname());
    authCookie.setMaxAge(3600);
    response.addCookie(authCookie);
    return new AuthResponse("Авторизация успешна. Куки сохранены");
  }

  @PostMapping("logout")
  public AuthResponse logout(HttpServletResponse response,
                             @CookieValue(value = "authUser", required = false) Cookie authUser) {
    authUser.setMaxAge(0);
    response.addCookie(authUser);
    return new AuthResponse("Разавторизация");
  }

}
