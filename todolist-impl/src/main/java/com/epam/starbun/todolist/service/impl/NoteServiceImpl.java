package com.epam.starbun.todolist.service.impl;

import com.epam.starbun.todolist.domain.NoteEntity;
import com.epam.starbun.todolist.repository.NoteRepository;
import com.epam.starbun.todolist.service.NoteService;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

  private final NoteRepository noteRepository;

  private final MapperFacade mapperFacade;

  @Override
  public NoteEntity save(NoteEntity note) {
    note.setCreationDate(OffsetDateTime.now());
    return noteRepository.save(note);
  }
}
