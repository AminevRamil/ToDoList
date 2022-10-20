package com.epam.starbun.todolist.service.impl;

import com.epam.starbun.todolist.domain.UserEntity;
import com.epam.starbun.todolist.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.epam.starbun.todolist.util.TestUtils.getDefaultUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
@RunWith(SpringRunner.class)
class UserServiceImplTest {

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository userRepository;

  @Test
  void success_addUser() {
    UserEntity userToSave = getDefaultUser();
    userService.save(userToSave);
//    UserEntity savedUser = userService.findById(userToSave.getId());
//    Assert.assertEquals(userToSave, savedUser);
    verify(userRepository, times(1)).save(any());
  }
}