package com.epam.starbun.todolist.controller.impl;


import com.epam.starbun.todolist.domain.UserEntity;
import com.epam.starbun.todolist.dto.UserDto;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserControllerImpl {

  private final MapperFacade mapperFacade;

  private final UserService userService;

  @GetMapping({"", "/"})
  public String index(Model model) {
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