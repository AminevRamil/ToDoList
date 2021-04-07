package com.epam.starbun.todolist.controller.impl;

import com.epam.starbun.todolist.controller.NewNoteController;
import com.epam.starbun.todolist.dto.NoteDto;
import com.epam.starbun.todolist.service.NoteService;
import com.epam.starbun.todolist.service.UserService;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/new-note")
@RequiredArgsConstructor
public class NewNoteControllerImpl implements NewNoteController {

  private final NoteService noteService;

  private final UserService userService;

  @Override
  @PostMapping("/save")
  public String saveNote(Model model, @ModelAttribute("saveNote") NoteDto note, @CookieValue(name = "authUser") Cookie authUser) {

//    UserEntity user = userService.findByNickname(authUser.getValue());
//    note.getUserList().add(user);
//    noteService.save(note);
//    model.addAttribute("currentUser", authUser.getValue());
    return "my-notes";
  }
}
