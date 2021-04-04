package com.epam.starbun.todolist.service;

import com.epam.starbun.todolist.dto.Note;
import org.springframework.stereotype.Service;

@Service
public interface NoteService {

  Note save(Note note);

}
