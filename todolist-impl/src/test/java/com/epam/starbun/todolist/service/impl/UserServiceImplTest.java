package com.epam.starbun.todolist.service.impl;

import com.epam.starbun.todolist.repository.UserRepository;
import ma.glasnost.orika.MapperFacade;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {
  @Autowired
  private UserServiceImpl userService;

  @Autowired
  private MapperFacade mapperFacade;

  @Mock
  private UserRepository userRepository;

  @Test
  public void success_addUser() {

  }

}