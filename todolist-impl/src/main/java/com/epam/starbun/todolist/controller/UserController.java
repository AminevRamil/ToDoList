package com.epam.starbun.todolist.controller;


import com.epam.starbun.todolist.domain.User;
import com.epam.starbun.todolist.repository.UserRepository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/welcome")
  public String welcome() {
    log.info("Welcome page is returned");
    return "welcome";
  }

  @GetMapping("/")
  public String index(Model model) {
//    List<User> users = userRepository.findAll();
//    model.addAttribute("users", users);
    return "users";
  }

  @PostMapping(value = "/search")
  public String hello(Model model, @RequestParam(defaultValue = "") String searchName) {
    List<User> filterList = userRepository.findAll().stream()
        .filter(user -> user.getNickname().equals(searchName))
        .collect(Collectors.toList());

    model.addAttribute("users", filterList);
    model.addAttribute("lastSearch", searchName);
    return "users";
  }

  @PostMapping(value = "/save")
  public String save(Model model, @ModelAttribute("user") User user) {
    user.setId(UUID.randomUUID());
    user.setCreationDate(OffsetDateTime.now());
    userRepository.save(user);
//    List<User> users = userRepository.findAll();
//    model.addAttribute("users", users);
    return "users";
  }
}