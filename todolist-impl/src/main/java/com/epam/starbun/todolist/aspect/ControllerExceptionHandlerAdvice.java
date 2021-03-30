package com.epam.starbun.todolist.aspect;

import com.epam.starbun.todolist.dto.RequestResponse;
import com.epam.starbun.todolist.exception.RequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.epam.starbun.todolist.resource")
public class ControllerExceptionHandlerAdvice {

  @ExceptionHandler
  public ResponseEntity<RequestResponse> handleException(RequestException e) {
    RequestResponse errorResponse = new RequestResponse();
    errorResponse.setStatus("Ошибка");
    errorResponse.setResults(e.getErrors());
    return new ResponseEntity<>(errorResponse, e.getStatus());
  }

}
