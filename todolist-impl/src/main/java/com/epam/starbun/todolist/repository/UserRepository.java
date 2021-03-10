package com.epam.starbun.todolist.repository;

import com.epam.starbun.todolist.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  // TODO Сделать поле никнейм уникальным и добавить ошибки при попытке сохранить
  User findFirstByNicknameEquals(String nickname);
}
