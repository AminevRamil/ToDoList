package com.epam.starbun.todolist.aspect;

import com.epam.starbun.todolist.exception.RequestException;
import com.epam.starbun.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static com.epam.starbun.todolist.util.ControllerUtils.getCookieByName;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginCheckAspect {

  private final UserService userService;

  @Pointcut("@annotation(com.epam.starbun.todolist.annotation.CheckLogin) && args(..,request)")
  public void beforeCallAtCheckLogin(HttpServletRequest request) {
  }

  @Around(value = "beforeCallAtCheckLogin(request)", argNames = "pjp, request")
  public Object aroundCallAt(ProceedingJoinPoint pjp, HttpServletRequest request) {
    Cookie authCookie = getCookieByName(request, "authCookie");
    if (authCookie == null || userService.findOneByNickname(authCookie.getValue()) == null) {
      RequestException re = new RequestException("Вы не залогинены");
      re.setStatus(HttpStatus.UNAUTHORIZED);
      throw re;
    }
    try {
      return pjp.proceed();
    } catch (RequestException re) {
      throw re; //Пробрасываю, чтоб бросаемые котроллером исключения обрабатывались *ExceptionHandlerAdvice
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      RequestException re = new RequestException("Внутренняя ошибка сервера. Свяжитесь с администратором");
      re.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      throw re;
    }
  }
}
