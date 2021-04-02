package com.epam.starbun.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/new-note")
@RequiredArgsConstructor
public class NewNoteController {

  @PostMapping("/save")
  public String saveNote(Model model) {
    return "";
  }
}
