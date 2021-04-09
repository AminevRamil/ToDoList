package com.epam.starbun.todolist.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice(basePackages = "com.epam.starbun.todolist.controller")
public class ControllerExceptionHandlerImpl {

  @ExceptionHandler
  public ModelAndView handleRestRequestException(RequestException e, HttpServletRequest request) {
    log.error("Exception: ", e);
    ModelAndView model = new ModelAndView(extractUri(request));
    model.addObject("violations", e.getErrors());
    return model;
  }

  @ExceptionHandler
  public ModelAndView handleWebPageException(BindException e, HttpServletRequest request) {
    log.error("Validation exception: ", e);
    List<String> violations = e.getFieldErrors().stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage)
      .collect(Collectors.toList());
    ModelAndView model = new ModelAndView(extractUri(request));
    model.addObject("violations", violations);
    return model;
  }

  @ExceptionHandler
  public ModelAndView handleMissingRequestCookieException(MissingRequestCookieException e) {
    ModelAndView model = new ModelAndView("login");
    if (e.getCookieName().equals("authUser")) {
      log.warn("Unauthorized access attempt");
      model.addObject("violations", Collections.singletonList("Вы не авторизованы"));
    } else {
      log.warn("Exception: ", e);
    }
    return model;
  }

  private String extractUri(HttpServletRequest request) {
    String uri = request.getRequestURI();
    return uri.substring(0, uri.lastIndexOf("/"));
  }
}
