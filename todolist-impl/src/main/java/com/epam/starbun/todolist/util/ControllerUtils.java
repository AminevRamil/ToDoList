package com.epam.starbun.todolist.util;

import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ControllerUtils {

  public Cookie getCookieByName(HttpServletRequest request, String cookieName) {

    return Arrays.stream(request.getCookies())
        .filter(cookie -> cookie.getName().equals(cookieName))
        .findFirst().orElse(null);
  }
//
//  public static void removeCookieIfExist(HttpReq)
}
