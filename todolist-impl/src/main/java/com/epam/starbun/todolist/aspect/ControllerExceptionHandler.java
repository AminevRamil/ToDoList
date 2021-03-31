package com.epam.starbun.todolist.aspect;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(basePackages = "com.epam.starbun.todolist.controller")
public class ControllerExceptionHandler {

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
