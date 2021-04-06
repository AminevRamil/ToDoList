package com.epam.starbun.todolist.resource;

import com.epam.starbun.todolist.dto.AuthRequest;
import com.epam.starbun.todolist.dto.RequestResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public interface LoginResource {
  @PostMapping("/login")
  RequestResponse login(@Validated @RequestBody AuthRequest authRequest, HttpServletResponse response);

  @PostMapping("/logout")
  RequestResponse logout(HttpServletResponse response,
                         @CookieValue(value = "authUser", required = false) Cookie authUser);
}
