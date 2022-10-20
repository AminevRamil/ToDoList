package com.epam.starbun.todolist.service.impl;

import com.epam.starbun.todolist.domain.NoteEntity;
import com.epam.starbun.todolist.domain.UserEntity;
import com.epam.starbun.todolist.exception.RequestException;
import com.epam.starbun.todolist.repository.NoteRepository;
import com.epam.starbun.todolist.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

  private final NoteRepository noteRepository;

  @Override
  public NoteEntity save(NoteEntity note) {
    note.setIsActive(true);
    note.setCreationDate(OffsetDateTime.now());
    return noteRepository.save(note);
  }

  @Override
  public List<NoteEntity> getActiveNotesOfUser(UserEntity user) {
    return noteRepository.findByUserId(user.getId()).stream()
      .filter(NoteEntity::getIsActive)
      .collect(Collectors.toList());
  }

  @Override
  public void deactivate(Long id) {
    NoteEntity noteEntity = noteRepository.findById(id)
      .orElseThrow(() -> new RequestException("Не сущуствует заметки с id=" + id));
    noteEntity.setIsActive(false);
    noteRepository.saveAndFlush(noteEntity);
  }

  @Override
  public List<NoteEntity> getAllNotNotifiedNotes() {
    return noteRepository.findAllNotNotifiedNotes();
  }
}
