package com.epam.starbun.todolist.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserDto {

  private String nickname;
  private String email;
  private String password;
}
