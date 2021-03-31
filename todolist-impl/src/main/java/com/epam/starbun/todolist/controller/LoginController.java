package com.epam.starbun.todolist.controller;

import com.epam.starbun.todolist.dto.User;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

  private final UserService userService;

  @GetMapping({"/", ""})
  public String login(Model model) {
    return "login";
  }

  @PostMapping("/save")
  public String save(Model model, @Valid @ModelAttribute("user") User user) {
    userService.save(user);
    model.addAttribute("result", "success");
    return "login";
  }
}
