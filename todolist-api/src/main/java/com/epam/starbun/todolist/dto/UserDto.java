package com.epam.starbun.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
//@Validation
public class UserDto {

  private UUID id;
  private String nickname;
  private String email;
  private String password;
}
