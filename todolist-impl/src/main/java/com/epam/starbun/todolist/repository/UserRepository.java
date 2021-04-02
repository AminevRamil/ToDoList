package com.epam.starbun.todolist.repository;

import com.epam.starbun.todolist.domain.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  List<UserEntity> findByNicknameLikeIgnoreCase(String nickname);
}
