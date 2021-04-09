package com.epam.starbun.todolist.dto;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class NoteDto {

  private Long id;

  private List<UserDto> userList = new ArrayList<>();

  private List<AttachmentDto> attachmentList = new ArrayList<>();

  private String title;
  private String body;
  private OffsetDateTime endDate;
  private OffsetDateTime creationDate;
  private OffsetDateTime updateDate;
}
