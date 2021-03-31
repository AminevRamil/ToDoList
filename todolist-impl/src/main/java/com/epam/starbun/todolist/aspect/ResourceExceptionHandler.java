package com.epam.starbun.todolist.aspect;


import com.epam.starbun.todolist.dto.RequestResponse;
import com.epam.starbun.todolist.exception.RequestException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.epam.starbun.todolist.resource")
public class ResourceExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<RequestResponse> handleRestRequestException(RequestException e) {
    RequestResponse errorResponse = new RequestResponse();
    errorResponse.setStatus("Ошибка");
    errorResponse.setResults(e.getErrors());
    return new ResponseEntity<>(errorResponse, e.getStatus());
  }

  @ExceptionHandler
  public ResponseEntity<RequestResponse> handleValidationException(BindException e) {
    String[] violations = e.getFieldErrors().stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage)
      .toArray(String[]::new);
    RequestResponse errorResponse = new RequestResponse();
    errorResponse.setStatus("Ошибка");
    errorResponse.setResults(violations);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
