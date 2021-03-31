package com.epam.starbun.todolist.aspect;

import com.epam.starbun.todolist.dto.RequestResponse;
import com.epam.starbun.todolist.exception.RequestException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice(basePackages = "com.epam.starbun.todolist.resource")
public class ControllerExceptionHandlerAdvice {

  @ExceptionHandler
  public ResponseEntity<RequestResponse> handleRestException(RequestException e) {
    RequestResponse errorResponse = new RequestResponse();
    errorResponse.setStatus("Ошибка");
    errorResponse.setResults(e.getErrors());
    return new ResponseEntity<>(errorResponse, e.getStatus());
  }

  @ExceptionHandler
  public ModelAndView handleWebPageException(BindException e, HttpServletRequest request) {
    List<String> violations = e.getFieldErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
    ModelAndView model = new ModelAndView(extractUri(request));
    model.addObject("violations", violations);
    return model;
  }

  private String extractUri(HttpServletRequest request) {
    String uri = request.getRequestURI();
    return uri.substring(0, uri.lastIndexOf("/"));
  }
}
