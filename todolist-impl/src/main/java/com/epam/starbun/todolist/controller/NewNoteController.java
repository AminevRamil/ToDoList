package com.epam.starbun.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static com.epam.starbun.todolist.util.ControllerUtils.getCookieByName;

@Controller
@RequestMapping("/new-note")
@RequiredArgsConstructor
public class NewNoteController {

  @PostMapping("/save")
  public String saveNote(Model model, HttpServletRequest request) {
    Cookie authUser = getCookieByName(request, "authUser");

    return "/";
  }

  @GetMapping("/save")
  public String asd(Model model, HttpServletRequest request) {
    Cookie authUser = getCookieByName(request, "authUser");

    return "/my-notes";
  }
}
