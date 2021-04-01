package com.epam.starbun.todolist.controller;

import com.epam.starbun.todolist.dto.AuthRequest;
import com.epam.starbun.todolist.dto.User;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

  private final UserService userService;

  @PostMapping("/save")
  public String save(Model model, @Valid @ModelAttribute("user") User user) {
    userService.save(user);
    // TODO заменить на модальное окно
    model.addAttribute("result", "Регистрация успешна");
    return "login";
  }

  @PostMapping("/logon")
  public String authorize(Model model, @Valid @ModelAttribute("authData") AuthRequest authData, HttpServletResponse response) {
    User user = userService.authorizeUser(authData);
    return "main";
  }
}
