package com.epam.starbun.todolist.dto;


import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class NoteDto {

  private Long id;

  private List<UserDto> userList = new ArrayList<>();

  private List<AttachmentDto> attachmentList = new ArrayList<>();

  private String title;
  private String body;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;
  private Boolean isActive;
  private OffsetDateTime creationDate;
  private OffsetDateTime updateDate;
}
