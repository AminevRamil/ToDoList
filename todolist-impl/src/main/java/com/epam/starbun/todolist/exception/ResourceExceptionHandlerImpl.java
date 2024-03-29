package com.epam.starbun.todolist.exception;

import com.epam.starbun.todolist.dto.RequestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.epam.starbun.todolist.resource")
public class ResourceExceptionHandlerImpl {

  @ExceptionHandler(RequestException.class)
  public ResponseEntity<RequestResponse> handleRestRequestException(RequestException e) {
    log.error("RequestException: {}", e.getLocalizedMessage());
    RequestResponse errorResponse = new RequestResponse();
    errorResponse.setStatus("Ошибка");
    errorResponse.setResults(e.getErrors());
    return new ResponseEntity<>(errorResponse, e.getStatus());
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<RequestResponse> handleValidationException(BindException e) {
    log.warn("Validation exception: ", e);
    String[] violations = e.getFieldErrors().stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage)
      .toArray(String[]::new);
    RequestResponse errorResponse = new RequestResponse();
    errorResponse.setStatus("Ошибка");
    errorResponse.setResults(violations);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<RequestResponse> handleRuntimeException(RuntimeException e) {
    log.error("Unexpected exception: ", e);
    RequestResponse errorResponse = new RequestResponse();
    errorResponse.setStatus("Ошибка");
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
