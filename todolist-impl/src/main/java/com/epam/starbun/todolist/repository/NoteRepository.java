package com.epam.starbun.todolist.repository;

import com.epam.starbun.todolist.domain.NoteEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

  @Query("select ne from NoteEntity ne inner join UserEntity ue on ue.id = :id")
  List<NoteEntity> findByUserId(Long id);
}
