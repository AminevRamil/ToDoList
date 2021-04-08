package com.epam.starbun.todolist.repository;

import com.epam.starbun.todolist.domain.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

  @Query("SELECT ne FROM UserEntity ue JOIN ue.noteList ne ON ue.id = :id")
  List<NoteEntity> findByUserId(Long id);
}
