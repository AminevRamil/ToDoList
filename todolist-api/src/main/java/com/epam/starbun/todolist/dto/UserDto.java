package com.epam.starbun.todolist.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
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
