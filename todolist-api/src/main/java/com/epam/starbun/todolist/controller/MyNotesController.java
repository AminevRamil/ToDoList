package com.epam.starbun.todolist.controller;

import javax.websocket.server.PathParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/my-notes")
public interface MyNotesController {

  @RequestMapping("/resolve")
  String resolve(Model model, @PathParam("id") Long id, @CookieValue(name = "authUser") String authUser);
}
