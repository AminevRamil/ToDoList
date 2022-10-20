package com.epam.starbun.todolist.util;

import com.epam.starbun.todolist.domain.UserEntity;

import java.time.OffsetDateTime;

public class TestUtils {

  public static UserEntity getDefaultUser() {
    UserEntity user = new UserEntity();
    user.setId(1L);
    user.setCreationDate(OffsetDateTime.now());
    user.setEmail("a@b.com");
    user.setNickname("Aaaaaaa");
    user.setPassword("Aaaaaaa1");
    return user;
  }
}
