package com.epam.starbun.todolist.controller.impl;

import com.epam.starbun.todolist.controller.RootController;
import com.epam.starbun.todolist.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RootControllerImpl implements RootController {

  UserService userService;

  @Override
  @GetMapping({"", "/"})
  public String index() {
    return "login";
  }

  @Override
  @GetMapping({"/login"})
  public String loginPage(Model model, @CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser", authUser);
    return "login";
  }

  @Override
  @GetMapping({"/logout"})
  public String logout(@CookieValue("authUser") Cookie authUser, HttpServletResponse response) {
    authUser.setMaxAge(0);
    response.addCookie(authUser);
    return "login";
  }

  @Override
  @GetMapping("/main")
  public String mainPage(Model model, @CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser", authUser);
    return "main";
  }

  @Override
  @GetMapping("/setting")
  public String settingsPage(Model model, @CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser", authUser);
    return "setting";
  }

  @Override
  @GetMapping("/new-note")
  public String newNote(Model model, @CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser", authUser);
    return "new-note";
  }

  @Override
  @GetMapping("/my-notes")
  public String myNotes(Model model, @CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser", authUser);
    return "my-notes";
  }

}
