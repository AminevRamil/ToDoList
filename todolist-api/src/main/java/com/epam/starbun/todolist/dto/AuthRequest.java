package com.epam.starbun.todolist.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthRequest {
  @NotEmpty(message = "Логин должен быть заполнен")
  private String login;
  @NotEmpty(message = "Пароль должен быть заполнен")
  private String password;
}
