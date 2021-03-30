package com.epam.starbun.todolist.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class RequestException extends RuntimeException {

  @Getter
  private final String[] errors;

  @Getter
  @Setter
  private HttpStatus status = HttpStatus.BAD_REQUEST;

  public RequestException(String... errors) {
    super();
    this.errors = errors;
  }
}
