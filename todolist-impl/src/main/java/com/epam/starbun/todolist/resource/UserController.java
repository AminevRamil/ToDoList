package com.epam.starbun.todolist.resource;


import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  private final Validator validator;

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

  @PostMapping(value = "/reset")
  public String resetCookie(Model model, @CookieValue(value = "lastSearch", required = false) Cookie searchCookie, HttpServletResponse response) {
    searchCookie.setMaxAge(0);
    response.addCookie(searchCookie);
    List<UserDto> users = userService.findAll();
    model.addAttribute("users", users);
    return "users";
  }

  @PostMapping(value = "/save")
  public String save(Model model, @ModelAttribute("user") UserDto userDto) {

    Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
    if (!violations.isEmpty()) {
      List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
      model.addAttribute("violations", errorMessages);
    } else {
      userService.save(userDto);
    }
    List<UserDto> users = userService.findAll();
    model.addAttribute("users", users);
    return "users";
  }
}