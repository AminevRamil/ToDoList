package com.epam.starbun.todolist.exception;

import lombok.Getter;

public class RequestException extends RuntimeException {

  @Getter
  private final String[] errors;

  public RequestException(String[] errors) {
    super();
    this.errors = errors;
  }
}
