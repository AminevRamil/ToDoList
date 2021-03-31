package com.epam.starbun.todolist.resource;

import com.epam.starbun.todolist.dto.User;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

  @GetMapping({"/", ""})
  public String login(Model model) {
    return "login";
  }

  @PostMapping("/save")
  public String save(Model model, @Valid @ModelAttribute("user") User user) {

    return "login";
  }
}
