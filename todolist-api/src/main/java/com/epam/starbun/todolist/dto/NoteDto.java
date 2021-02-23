package com.epam.starbun.todolist.dto;


import java.time.OffsetDateTime;
import java.util.List;
import lombok.Data;

@Data
public class NoteDto {

  private List<AttachmentDto> attachmentList;

  private String title;
  private String body;
  private OffsetDateTime endDate;
  private OffsetDateTime creationDate;
  private OffsetDateTime updateDate;
}
