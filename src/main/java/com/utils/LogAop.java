package com.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {
	@Pointcut("execution(* com.cn.service.*.save*(..))")
         //第一个* 说明方法的类型
         //第二个* 说明包下的所有的类  如果是包下类和自包中的类* com.cn..*.save*(..))
         //(..)方法中的参数
      	private void anyMethod() {
	}// 定义一个切入点

	@Pointcut("execution(* com.cn.service.*.update*(..))")
	private void doMethod() {
	}// 定义一个切入点

	@Before("anyMethod()&&args(name)")
	public void doAccessCheck(String name) {
		System.out.println(name);
		System.out.println("前置通知");
	}

	/* @AfterReturning("anyMethod()") */
	@AfterReturning("anyMethod()")
	public void doAfter() {
		System.out.println("后置通知");
	}

	@After("anyMethod()")
	public void after() {
		System.out.println("最终通知");
	}

	@AfterThrowing("anyMethod()")
	public void doAfterThrow() {
		System.out.println("例外通知");
	}

	@Around("anyMethod()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("进入环绕通知");
		Object object = pjp.proceed();// 执行该方法
		System.out.println("退出方法");
		return object;
	}

	@Around("doMethod()")
	public Object doBasic(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("进入环绕通知...");
		Object object = pjp.proceed();// 执行该方法
		System.out.println("退出方法...");
		return object;
	}


}  
   