package com.epam.starbun.todolist.controller;

import com.epam.starbun.todolist.dto.NoteDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;

public interface NewNoteController {
  @PostMapping("/save")
  String saveNote(Model model, @ModelAttribute("saveNote") NoteDto note, @CookieValue(name = "authUser") Cookie authUser);
}
