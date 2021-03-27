package com.epam.starbun.todolist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestResponse {
  private String status;
  private String[] results;

  public RequestResponse(String status, String... result) {
    this.status = status;
    this.results = result;
  }
}
