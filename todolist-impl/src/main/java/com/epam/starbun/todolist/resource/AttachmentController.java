package com.epam.starbun.todolist.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttachmentController {

  @GetMapping("attach")
  public String start() {
    return "attach";
  }
}
