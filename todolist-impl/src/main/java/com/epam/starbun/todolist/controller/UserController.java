package com.epam.starbun.todolist.controller;


import com.epam.starbun.todolist.domain.User;
import com.epam.starbun.todolist.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/")
  public String index(Model model, @CookieValue(value = "lastSearch", required = false) Cookie searchCookie) {
    List<User> users = userRepository.findAll();
    model.addAttribute("users", users);
    //Проверка наличия куки
    if (searchCookie != null) {
      String searchName = searchCookie.getValue();
      List<User> filterList = userRepository.findByNicknameEquals(searchName);
      model.addAttribute("users", filterList);
      model.addAttribute("lastSearch", searchName);
    }
    return "users";
  }

  @PostMapping(value = "/search")
  public String searchUser(Model model, @RequestParam(defaultValue = "") String searchName, HttpServletResponse response) {
    List<User> filterList = userRepository.findByNicknameEquals(searchName);
    model.addAttribute("users", filterList);
    model.addAttribute("lastSearch", searchName);
    //запись последнего поиска в куки
    Cookie lastSearch = new Cookie("lastSearch", searchName);
    lastSearch.setMaxAge(3600);
    response.addCookie(lastSearch);
    return "users";
  }

  @PostMapping(value = "/save")
  public String save(Model model, @ModelAttribute("user") User user) {
    user.setId(UUID.randomUUID());
    user.setCreationDate(OffsetDateTime.now());
    userRepository.save(user);
    List<User> users = userRepository.findAll();
    model.addAttribute("users", users);
    return "users";
  }
}