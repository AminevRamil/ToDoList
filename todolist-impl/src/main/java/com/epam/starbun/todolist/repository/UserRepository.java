package com.epam.starbun.todolist.repository;

import com.epam.starbun.todolist.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  List<UserEntity> findByNicknameLike(String nickname);
}
