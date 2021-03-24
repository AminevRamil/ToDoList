package com.epam.starbun.todolist.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspect {

  @Pointcut("execution(* com.epam.starbun.todolist.resource.*.*(..))")
  public void before(JoinPoint joinPoint) {
    System.out.println("asdasdasd");
  }

}
