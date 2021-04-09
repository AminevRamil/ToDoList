package com.epam.starbun.todolist.controller;

import com.epam.starbun.todolist.dto.AuthRequest;
import com.epam.starbun.todolist.dto.UserDto;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
public interface LoginController {

  @PostMapping("/save")
  String save(Model model, @Valid @ModelAttribute("user") UserDto user);

  @PostMapping("/logon")
  String authorize(Model model, @Valid @ModelAttribute("authData") AuthRequest authData,
      HttpServletResponse response);
}
