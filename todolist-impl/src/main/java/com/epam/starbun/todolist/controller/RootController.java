package com.epam.starbun.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

  @GetMapping({"", "/"})
  public String index() {
    return "login";
  }

  @GetMapping({"/login"})
  public String loginPage(Model model) {
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
  public String newNote() {
    return "new-note";
  }

  @GetMapping("/my-notes")
  public String myNotes() {
    return "my-notes";
  }

}
