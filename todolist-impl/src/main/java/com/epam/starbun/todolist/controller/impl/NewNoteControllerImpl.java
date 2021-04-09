package com.epam.starbun.todolist.controller.impl;

import com.epam.starbun.todolist.controller.NewNoteController;
import com.epam.starbun.todolist.domain.NoteEntity;
import com.epam.starbun.todolist.domain.UserEntity;
import com.epam.starbun.todolist.dto.NoteDto;
import com.epam.starbun.todolist.service.NoteService;
import com.epam.starbun.todolist.service.UserService;
import java.util.List;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;

@Controller
@RequiredArgsConstructor
public class NewNoteControllerImpl implements NewNoteController {

  private final NoteService noteService;

  private final UserService userService;

  private final MapperFacade mapperFacade;

  @Override
  public String saveNote(Model model, NoteDto note,
      @CookieValue(name = "authUser") Cookie authUser) {

    UserEntity user = userService.findByNickname(authUser.getValue());
    NoteEntity noteEntity = mapperFacade.map(note, NoteEntity.class);
    noteEntity.getUserEntityList().add(user);
    noteService.save(noteEntity);

    model.addAttribute("currentUser", user.getNickname());

    List<NoteEntity> notesOfUser = noteService.getNotesOfUser(user);
    model.addAttribute("notes", mapperFacade.mapAsList(notesOfUser, NoteDto.class));
    return "my-notes";
  }
}
