package com.epam.starbun.todolist.resource;


import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

  @Autowired
  private final UserService userService;

  @GetMapping("/")
  public String index(Model model, @CookieValue(value = "lastSearch", required = false) Cookie searchCookie) {
    List<UserDto> users = userService.findAll();
    model.addAttribute("users", users);
    //Проверка наличия куки
    if (searchCookie != null) {
      String searchName = searchCookie.getValue();
      model.addAttribute("lastSearch", searchName);
      List<UserDto> userDtoList = userService.findByNickname(searchName);
      model.addAttribute("lastSearch", searchName);
      model.addAttribute("users", userDtoList);
    }
    return "users";
  }

  @PostMapping(value = "/search")
  public String searchUser(Model model, @RequestParam(defaultValue = "") String searchName, HttpServletResponse response) {
    List<UserDto> userDtoList = userService.findByNickname(searchName);
    model.addAttribute("lastSearch", searchName);
    model.addAttribute("users", userDtoList);
    //запись последнего поиска в куки
    Cookie lastSearch = new Cookie("lastSearch", searchName);
    lastSearch.setMaxAge(3600);
    response.addCookie(lastSearch);
    return "users";
  }

  @PostMapping(value = "/save")
  public String save(Model model, @ModelAttribute("user") UserDto userDto) {
    userService.save(userDto);
    List<UserDto> users = userService.findAll();
    model.addAttribute("users", users);
    return "users";
  }
}