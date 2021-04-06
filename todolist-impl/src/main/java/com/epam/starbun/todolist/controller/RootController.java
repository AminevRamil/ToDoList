package com.epam.starbun.todolist.controller;

import com.epam.starbun.todolist.service.UserService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

  UserService userService;

  @GetMapping({"", "/"})
  public String index() {
    return "login";
  }

  @GetMapping({"/login"})
  public String loginPage(Model model, @CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser", authUser);
    return "login";
  }

  @GetMapping({"/logout"})
  public String logout(@CookieValue("authUser") Cookie authUser, HttpServletResponse response) {
    authUser.setMaxAge(0);
    response.addCookie(authUser);
    return "login";
  }

  @GetMapping("/main")
  public String mainPage(Model model, @CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser", authUser);
    return "main";
  }

  @GetMapping("/setting")
  public String settingsPage(Model model, @CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser", authUser);
    return "setting";
  }

  @GetMapping("/new-note")
  public String newNote(Model model, @CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser", authUser);
    return "new-note";
  }

  @GetMapping("/my-notes")
  public String myNotes(Model model, @CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser", authUser);
    return "my-notes";
  }

}
