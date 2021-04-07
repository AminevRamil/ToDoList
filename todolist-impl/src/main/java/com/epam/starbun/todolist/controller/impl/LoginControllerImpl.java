package com.epam.starbun.todolist.controller.impl;

import com.epam.starbun.todolist.controller.LoginController;
import com.epam.starbun.todolist.domain.UserEntity;
import com.epam.starbun.todolist.dto.AuthRequest;
import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.service.UserService;
import java.time.OffsetDateTime;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginControllerImpl implements LoginController {

  private final MapperFacade mapperFacade;

  private final UserService userService;

  @Override
  @PostMapping("/save")
  public String save(Model model, @Valid @ModelAttribute("user") UserDto user) {
    UserEntity userEntity = mapperFacade.map(user, UserEntity.class);
    userEntity.setCreationDate(OffsetDateTime.now());
    userService.save(userEntity);
    model.addAttribute("result", "Регистрация успешна");
    return "login";
  }

  @Override
  @PostMapping("/logon")
  public String authorize(Model model, @Valid @ModelAttribute("authData") AuthRequest authData,
                          HttpServletResponse response) {
    UserEntity user = userService.authorizeUser(authData);
    Cookie authUser = new Cookie("authUser", user.getNickname());
    model.addAttribute("currentUser", user.getNickname());
    authUser.setMaxAge(3600);
    authUser.setPath("/");
    response.addCookie(authUser);
    return "main";
  }
}
