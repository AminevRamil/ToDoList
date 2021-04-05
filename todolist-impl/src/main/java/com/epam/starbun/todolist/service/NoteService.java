package com.epam.starbun.todolist.service;

import com.epam.starbun.todolist.dto.NoteDto;
import org.springframework.stereotype.Service;

@Service
public interface NoteService {

  NoteDto save(NoteDto note);

}
