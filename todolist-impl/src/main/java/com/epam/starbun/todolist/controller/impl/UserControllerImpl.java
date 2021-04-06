package com.epam.starbun.todolist.controller.impl;


import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserControllerImpl {

  private final UserService userService;

  @GetMapping({"", "/"})
  public String index(Model model, @CookieValue(value = "lastSearch", required = false) Cookie searchCookie) {
    List<UserDto> users = userService.findAll();
    model.addAttribute("users", users);
    //Проверка наличия куки
    if (searchCookie != null) {
      String searchName = searchCookie.getValue();
      model.addAttribute("lastSearch", searchName);
      List<UserDto> userList = userService.findByNickname(searchName);
      model.addAttribute("lastSearch", searchName);
      model.addAttribute("users", userList);
    }
    return "user";
  }

  @PostMapping(value = "/search")
  public String searchUser(Model model, @RequestParam(defaultValue = "") String searchName, HttpServletResponse response) {
    List<UserDto> userList = userService.findByNickname(searchName);
    model.addAttribute("lastSearch", searchName);
    model.addAttribute("users", userList);
    //запись последнего поиска в куки
    Cookie lastSearch = new Cookie("lastSearch", searchName);
    lastSearch.setMaxAge(3600);
    lastSearch.setPath("/");
    response.addCookie(lastSearch);
    return "user";
  }

  @PostMapping(value = "/reset")
  public String resetCookie(Model model, @CookieValue(value = "lastSearch", required = false) Cookie searchCookie, HttpServletResponse response) {
    searchCookie.setMaxAge(0);
    response.addCookie(searchCookie);
    List<UserDto> users = userService.findAll();
    model.addAttribute("users", users);
    return "user";
  }

  @PostMapping(value = "/save")
  public String save(Model model, /*@Validated*/ @ModelAttribute("user") UserDto user) {
    userService.save(user);
    List<UserDto> users = userService.findAll();
    model.addAttribute("users", users);
    return "user";
  }
}