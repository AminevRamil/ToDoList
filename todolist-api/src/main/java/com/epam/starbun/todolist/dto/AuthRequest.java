package com.epam.starbun.todolist.dto;

import lombok.Data;

@Data
public class AuthRequest {
  private String login;
  private String password;
}
