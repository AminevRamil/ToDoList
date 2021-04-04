package com.epam.starbun.todolist.service.impl;

import com.epam.starbun.todolist.domain.NoteEntity;
import com.epam.starbun.todolist.dto.Note;
import com.epam.starbun.todolist.repository.NoteRepository;
import com.epam.starbun.todolist.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

  private final NoteRepository noteRepository;

  private final MapperFacade mapperFacade;

  @Override
  public Note save(Note note) {
    NoteEntity noteEntity = mapperFacade.map(note, NoteEntity.class);
    noteEntity.setCreationDate(OffsetDateTime.now());
    try {
      NoteEntity savedNoteEntity = noteRepository.save(noteEntity);
      return mapperFacade.map(savedNoteEntity, Note.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }
}
