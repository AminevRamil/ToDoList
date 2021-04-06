package com.epam.starbun.todolist.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public interface RootController {
  @GetMapping({"", "/"})
  String index();

  @GetMapping({"/login"})
  String loginPage(Model model, @CookieValue("authUser") String authUser);

  @GetMapping({"/logout"})
  String logout(@CookieValue("authUser") Cookie authUser, HttpServletResponse response);

  @GetMapping("/main")
  String mainPage(Model model, @CookieValue("authUser") String authUser);

  @GetMapping("/setting")
  String settingsPage(Model model, @CookieValue("authUser") String authUser);

  @GetMapping("/new-note")
  String newNote(Model model, @CookieValue("authUser") String authUser);

  @GetMapping("/my-notes")
  String myNotes(Model model, @CookieValue("authUser") String authUser);
}
