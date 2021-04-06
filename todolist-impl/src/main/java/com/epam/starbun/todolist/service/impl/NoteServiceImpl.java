package com.epam.starbun.todolist.service.impl;

import com.epam.starbun.todolist.domain.NoteEntity;
import com.epam.starbun.todolist.dto.NoteDto;
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
  public NoteDto save(NoteDto note) { // ToDo: 1
    NoteEntity noteEntity = mapperFacade.map(note, NoteEntity.class);
    noteEntity.setCreationDate(OffsetDateTime.now());
    try { // ToDo:Удалить try catch
      NoteEntity savedNoteEntity = noteRepository.save(noteEntity);
      return mapperFacade.map(savedNoteEntity, NoteDto.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }
}
