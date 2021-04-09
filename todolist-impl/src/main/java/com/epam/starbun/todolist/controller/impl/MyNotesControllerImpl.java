package com.epam.starbun.todolist.controller.impl;

import com.epam.starbun.todolist.controller.MyNotesController;
import com.epam.starbun.todolist.domain.NoteEntity;
import com.epam.starbun.todolist.domain.UserEntity;
import com.epam.starbun.todolist.dto.NoteDto;
import com.epam.starbun.todolist.service.NoteService;
import com.epam.starbun.todolist.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class MyNotesControllerImpl implements MyNotesController {

  private final UserService userService;

  private final NoteService noteService;

  private final MapperFacade mapperFacade;

  @Override
  public String resolve(Model model, Long id, String authUser) {
    // TODO Заменить удаление на резолв (исАктив). Добавить параметр в БД
    noteService.remove(id);

    model.addAttribute("currentUser", authUser);
    UserEntity user = userService.findByNickname(authUser);
    List<NoteEntity> notesOfUser = noteService.getNotesOfUser(user);
    model.addAttribute("notes", mapperFacade.mapAsList(notesOfUser, NoteDto.class));
    return "my-notes";
  }
}
