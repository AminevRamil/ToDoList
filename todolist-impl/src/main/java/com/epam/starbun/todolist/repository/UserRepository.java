package com.epam.starbun.todolist.repository;

import com.epam.starbun.todolist.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  List<User> findByNicknameEquals(String nickname);
}
