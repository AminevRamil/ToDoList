package com.epam.starbun.todolist.repository;

import com.epam.starbun.todolist.domain.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

}
