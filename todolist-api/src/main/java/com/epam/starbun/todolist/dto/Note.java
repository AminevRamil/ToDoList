package com.epam.starbun.todolist.dto;


import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Note {

  private List<User> userList = new ArrayList<>();

  private List<Attachment> attachmentList = new ArrayList<>();

  private String title;
  private String body;
  private OffsetDateTime endDate;
  private OffsetDateTime creationDate;
  private OffsetDateTime updateDate;
}
