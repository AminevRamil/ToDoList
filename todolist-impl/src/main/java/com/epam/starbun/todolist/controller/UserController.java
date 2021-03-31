package com.epam.starbun.todolist.controller;


import com.epam.starbun.todolist.dto.User;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @GetMapping({"", "/"})
  public String index(Model model, @CookieValue(value = "lastSearch", required = false) Cookie searchCookie) {
    List<User> users = userService.findAll();
    model.addAttribute("users", users);
    //Проверка наличия куки
    if (searchCookie != null) {
      String searchName = searchCookie.getValue();
      model.addAttribute("lastSearch", searchName);
      List<User> userList = userService.findByNickname(searchName);
      model.addAttribute("lastSearch", searchName);
      model.addAttribute("users", userList);
    }
    return "user";
  }

  @PostMapping(value = "/search")
  public String searchUser(Model model, @RequestParam(defaultValue = "") String searchName, HttpServletResponse response) {
    List<User> userList = userService.findByNickname(searchName);
    model.addAttribute("lastSearch", searchName);
    model.addAttribute("users", userList);
    //запись последнего поиска в куки
    Cookie lastSearch = new Cookie("lastSearch", searchName);
    lastSearch.setMaxAge(3600);
    response.addCookie(lastSearch);
    return "user";
  }

  @PostMapping(value = "/reset")
  public String resetCookie(Model model, @CookieValue(value = "lastSearch", required = false) Cookie searchCookie, HttpServletResponse response) {
    searchCookie.setMaxAge(0);
    response.addCookie(searchCookie);
    List<User> users = userService.findAll();
    model.addAttribute("users", users);
    return "user";
  }

  @PostMapping(value = "/save")
  public String save(Model model, @Validated @ModelAttribute("user") User user) {
    userService.save(user);
    List<User> users = userService.findAll();
    model.addAttribute("users", users);
    return "user";
  }
}