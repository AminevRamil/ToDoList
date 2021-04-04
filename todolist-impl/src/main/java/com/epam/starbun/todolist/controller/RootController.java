package com.epam.starbun.todolist.controller;

import com.epam.starbun.todolist.service.UserService;
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
  public String loginPage(Model model) {
    return "login";
  }

  //TODO Удаление кук
  @GetMapping({"/logout"})
  public String logout(Model model) {
    return "login";
  }

  @GetMapping("/main")
  public String mainPage() {
    return "main";
  }

  @GetMapping("/setting")
  public String settingsPage() {
    return "setting";
  }

  @GetMapping("/new-note")
  public String newNote(Model model, @CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser", authUser);
    return "new-note";
  }

  @GetMapping("/my-notes")
  public String myNotes() {
    return "my-notes";
  }

}
