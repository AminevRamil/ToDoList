package com.epam.starbun.todolist.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class User {

  private Long id = 0L;
  @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{5,16}$",
    message = "Никнейм должен состоять из латиницы, цифр и символа \"_\", и начинаться с буквы")
  private String nickname;
  @NotEmpty(message = "Почта должна быть заполнена")
  @Email(message = "Неверно указана е-почта")
  private String email;
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,50}$",
    message = "Пароль должен быть от 8 до 50 символов, содержать кам минимум по одной цифре, заглавной и строчной букве латиницы")
  private String password;
}
