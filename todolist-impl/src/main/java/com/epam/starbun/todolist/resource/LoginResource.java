package com.epam.starbun.todolist.resource;

import com.epam.starbun.todolist.dto.AuthRequest;
import com.epam.starbun.todolist.dto.RequestResponse;
import com.epam.starbun.todolist.dto.User;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class LoginResource {

  private final UserService userService;

  @PostMapping("/login")
  public RequestResponse login(@RequestBody AuthRequest authRequest, HttpServletResponse response,
                               @CookieValue(value = "authUser", required = false) String authUser) {
    if (authUser != null && userService.findOneByNickname(authUser) != null) {
      return new RequestResponse("Ошибка", "Вы уже авторизованы. Если хотите сменить пользователя, то необходимо разавторизоваться");
    }
    User user = userService.findOneByNickname(authRequest.getLogin());
    if (user == null && !user.getPassword().equals(authRequest.getPassword())) {
      return new RequestResponse("Ошибка", "Неверный логин или пароль");
    }

    Cookie authCookie = new Cookie("authUser", user.getNickname());
    authCookie.setMaxAge(3600);
    response.addCookie(authCookie);
    return new RequestResponse("Успешно", "Авторизация успешна. Куки сохранены");
  }

  @PostMapping("/logout")
  public RequestResponse logout(HttpServletResponse response,
                                @CookieValue(value = "authUser", required = false) Cookie authUser) {
    authUser.setMaxAge(0);
    response.addCookie(authUser);
    return new RequestResponse("Успешно","Разавторизация");
  }

}
