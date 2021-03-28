package com.epam.starbun.todolist.resource;

import com.epam.starbun.todolist.dto.AuthRequest;
import com.epam.starbun.todolist.dto.RequestResponse;
import com.epam.starbun.todolist.dto.User;
import com.epam.starbun.todolist.exception.RequestException;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class LoginResource {

  private final UserService userService;

  private final Validator validator;

  @PostMapping("/login")
  public RequestResponse login(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
    Set<ConstraintViolation<AuthRequest>> constraintViolations = validator.validate(authRequest);
    if (!constraintViolations.isEmpty()) {
      throw new RequestException(constraintViolations.stream().map(ConstraintViolation::getMessage).toArray(String[]::new));
    }
    User user = userService.findOneByNickname(authRequest.getLogin());
    if (user == null && !user.getPassword().equals(authRequest.getPassword())) {
      throw new RequestException("Неверный логин или пароль");
    }

    Cookie authCookie = new Cookie("authUser", user.getNickname());
    authCookie.setMaxAge(3600);
    response.addCookie(authCookie);
    return new RequestResponse("Успешно", "Авторизация успешна", "Куки сохранены");
  }

  @PostMapping("/logout")
  public RequestResponse logout(HttpServletResponse response,
                                @CookieValue(value = "authUser", required = false) Cookie authUser) {
    authUser.setMaxAge(0);
    response.addCookie(authUser);
    return new RequestResponse("Успешно","Разавторизация");
  }

}
