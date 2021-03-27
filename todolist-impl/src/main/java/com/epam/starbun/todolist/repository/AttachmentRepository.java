package com.epam.starbun.todolist.repository;

import com.epam.starbun.todolist.domain.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<AttachmentEntity, Long> {

}
