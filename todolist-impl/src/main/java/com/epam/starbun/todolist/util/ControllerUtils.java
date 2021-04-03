package com.epam.starbun.todolist.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class ControllerUtils {

  public static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
    return Arrays.stream(request.getCookies())
      .filter(cookie -> cookie.getName().equals(cookieName))
      .findFirst().orElse(null);
  }
}
