package com.epam.starbun.todolist.resource.impl;


import com.epam.starbun.todolist.dto.RequestResponse;
import com.epam.starbun.todolist.exception.RequestException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// TODO посмотреть экстенд от ResponseEntityExceptionHandler
@RestControllerAdvice(basePackages = "com.epam.starbun.todolist.resource")
public class ResourceExceptionHandlerImpl {

  @ExceptionHandler(RequestException.class)
  public ResponseEntity<RequestResponse> handleRestRequestException(RequestException e) {
    RequestResponse errorResponse = new RequestResponse();
    errorResponse.setStatus("Ошибка");
    errorResponse.setResults(e.getErrors());
    return new ResponseEntity<>(errorResponse, e.getStatus());
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<RequestResponse> handleValidationException(BindException e) {
    String[] violations = e.getFieldErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .toArray(String[]::new);
    RequestResponse errorResponse = new RequestResponse();
    errorResponse.setStatus("Ошибка");
    errorResponse.setResults(violations);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  // TODO Проверить
  @ExceptionHandler(Exception.class)
  public ResponseEntity<RequestResponse> handleValidationException(Exception e) {
    return null;
  }

}
