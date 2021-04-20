package com.epam.starbun.todolist.service;

import com.epam.starbun.todolist.domain.NoteEntity;
import com.epam.starbun.todolist.domain.UserEntity;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface NoteService {

  NoteEntity save(NoteEntity note);

  List<NoteEntity> getActiveNotesOfUser(UserEntity user);

  void deactivate(Long id);
}
