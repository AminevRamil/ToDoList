package com.epam.starbun.todolist.aspect;

import com.epam.starbun.todolist.exception.RequestException;
import com.epam.starbun.todolist.service.UserService;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoginCheckAspect {

  private final UserService userService;

  @Pointcut("@annotation(com.epam.starbun.todolist.annotation.CheckLogin) && args(..,authCookie)")
  public void beforeCallAtCheckLogin(Cookie authCookie) {
  }

  @Around(value = "beforeCallAtCheckLogin(authCookie)", argNames = "pjp,authCookie")
  public Object aroundCallAt(ProceedingJoinPoint pjp, Cookie authCookie) {
    if (authCookie == null || userService.findByNickname(authCookie.getValue()) == null) {
      RequestException re = new RequestException("Вы не залогинены");
      re.setStatus(HttpStatus.UNAUTHORIZED);
      throw re;
    }
    try {
      return pjp.proceed();
    } catch (RequestException re) {
      log.error("Exception: {}", re);
      throw re; //Пробрасываю, чтоб бросаемые котроллером исключения обрабатывались *ExceptionHandlerAdvice
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      RequestException re = new RequestException("Внутренняя ошибка сервера. Свяжитесь с администратором");
      re.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      throw re;
    }
  }
}
