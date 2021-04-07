package com.epam.starbun.todolist.controller.impl;


import com.epam.starbun.todolist.domain.UserEntity;
import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.service.UserService;
import java.time.OffsetDateTime;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserControllerImpl {

  private final MapperFacade mapperFacade;

  private final UserService userService;

  @GetMapping({"", "/"})
  public String index(Model model, @CookieValue(value = "lastSearch", required = false) Cookie searchCookie) {
    if (searchCookie != null) {
      String searchName = searchCookie.getValue();
      model.addAttribute("lastSearch", searchName);
      List<UserEntity> searchedUsersList = userService.findByNicknameLike(searchName);
      model.addAttribute("lastSearch", searchName);
      model.addAttribute("users", mapperFacade.mapAsList(searchedUsersList, UserDto.class));
    } else {
      List<UserEntity> allUsersList = userService.findAll();
      model.addAttribute("users", mapperFacade.mapAsList(allUsersList, UserDto.class));
    }
    return "user";
  }

  @PostMapping(value = "/search")
  public String searchUser(Model model, @RequestParam(defaultValue = "") String searchName, HttpServletResponse response) {
    List<UserEntity> userList = userService.findByNicknameLike(searchName);
    model.addAttribute("lastSearch", searchName);
    model.addAttribute("users", userList);

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
    List<UserEntity> allUsersList = userService.findAll();
    model.addAttribute("users", mapperFacade.mapAsList(allUsersList, UserDto.class));
    return "user";
  }

  @PostMapping(value = "/save")
  public String save(Model model, /*@Validated*/ @ModelAttribute("user") UserDto user) {
    UserEntity userEntity = mapperFacade.map(user, UserEntity.class);
    userEntity.setCreationDate(OffsetDateTime.now());
    userService.save(userEntity);
    List<UserEntity> allUsersList = userService.findAll();
    model.addAttribute("users", mapperFacade.mapAsList(allUsersList, UserDto.class));
    return "user";
  }
}