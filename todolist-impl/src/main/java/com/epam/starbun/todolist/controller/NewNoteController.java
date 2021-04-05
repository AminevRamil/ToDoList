package com.epam.starbun.todolist.controller;

import com.epam.starbun.todolist.dto.NoteDto;
import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.service.NoteService;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;

@Controller
@RequestMapping("/new-note")
@RequiredArgsConstructor
public class NewNoteController {

  private final NoteService noteService;

  private final UserService userService;

  @PostMapping("/save")
  public String saveNote(Model model, @ModelAttribute("saveNote") NoteDto note, @CookieValue(name = "authUser") Cookie authUser) {
    //Cookie authUser = getCookieByName(request, "authUser");
    UserDto user = userService.findOneByNickname(authUser.getValue());
    note.getUserList().add(user);
    noteService.save(note);
    model.addAttribute("currentUser", authUser.getValue());
    return "my-notes";
  }
}
