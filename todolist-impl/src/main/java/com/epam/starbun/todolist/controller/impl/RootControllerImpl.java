package com.epam.starbun.todolist.controller.impl;

import com.epam.starbun.todolist.controller.RootController;
import com.epam.starbun.todolist.domain.NoteEntity;
import com.epam.starbun.todolist.domain.UserEntity;
import com.epam.starbun.todolist.dto.NoteDto;
import com.epam.starbun.todolist.service.NoteService;
import com.epam.starbun.todolist.service.UserService;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class RootControllerImpl implements RootController {

  private final UserService userService;

  private final NoteService noteService;

  private final MapperFacade mapperFacade;

  @Override
  public String index() {
    return "login";
  }

  @Override
  public String loginPage(Model model, String authUser) {
    model.addAttribute("currentUser", authUser);
    return "login";
  }

  @Override
  public String logout(Cookie authUser, HttpServletResponse response) {
    authUser.setMaxAge(0);
    response.addCookie(authUser);
    return "login";
  }

  @Override
  public String mainPage(Model model, String authUser) {
    model.addAttribute("currentUser", authUser);
    return "main";
  }

  @Override
  public String settingsPage(Model model, String authUser) {
    model.addAttribute("currentUser", authUser);
    return "setting";
  }

  @Override
  public String newNote(Model model, String authUser) {
    model.addAttribute("currentUser", authUser);
    return "new-note";
  }

  @Override
  public String myNotes(Model model, String authUser) {
    UserEntity user = userService.findByNickname(authUser);

    model.addAttribute("currentUser", user.getNickname());

    List<NoteEntity> notesOfUser = noteService.getActiveNotesOfUser(user);
    model.addAttribute("notes", mapperFacade.mapAsList(notesOfUser, NoteDto.class));
    return "my-notes";
  }
}
