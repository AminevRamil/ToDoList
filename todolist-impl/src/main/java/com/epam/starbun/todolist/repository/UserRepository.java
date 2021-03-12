package com.epam.starbun.todolist.repository;

import com.epam.starbun.todolist.domain.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  List<User> findByNicknameLike(String nickname);
}
