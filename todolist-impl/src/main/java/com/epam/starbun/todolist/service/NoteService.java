package com.epam.starbun.todolist.service;

import com.epam.starbun.todolist.domain.NoteEntity;
import org.springframework.stereotype.Service;

@Service
public interface NoteService {

  NoteEntity save(NoteEntity note);

}
