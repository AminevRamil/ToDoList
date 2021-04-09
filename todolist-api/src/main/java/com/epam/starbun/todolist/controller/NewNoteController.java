package com.epam.starbun.todolist.controller;

import com.epam.starbun.todolist.dto.NoteDto;
import javax.servlet.http.Cookie;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/new-note")
public interface NewNoteController {

  @PostMapping("/save")
  String saveNote(Model model, NoteDto note, Cookie authUser);
}
