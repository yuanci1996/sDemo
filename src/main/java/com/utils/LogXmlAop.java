package com.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

/**
 * 统一日志aop
 */
@Component  //标记为一个
//@Aspect //标记为切面
public class LogXmlAop {
    private static final Logger log = LoggerFactory.getLogger(LogXmlAop.class);
 
    public LogXmlAop() {
    	System.out.println("*************************logstart************************");
    }
    //定义切点 方便复用
//    @Pointcut("execution(* com.service.impl.*.*(..))")
//    public void log(){};
 
    //前置通知
//    @Before("log()")
    public void beforeLog(JoinPoint jp){
    	System.out.println("进入before");
    	log.info("sss");
        log.info(jp.getSignature().getDeclaringTypeName()+"类的"+jp.getSignature().getName()+ "方法Before日志");
    }
 
    //环绕通知
//    @Around("log()")
    public Object aroundLog(ProceedingJoinPoint jp) {
    	Object result=null;
        try {
            log.info(jp.getSignature().getDeclaringTypeName()+"类的"+jp.getSignature().getName()+"方法Around通知开始");
            result=jp.proceed();
            log.info(jp.getSignature().getDeclaringTypeName() + "方法Around通知结束");
        }catch (Throwable throwable) {
            Object[] args = jp.getArgs();
 
            System.out.println("参数列表值为：");
            for (Object one: args){
                log.error(one.toString());
            }
            log.error(jp.getSignature().getDeclaringTypeName() + "类的" + jp.getSignature().getName() + "调用异常", throwable);
 
        }
		return result;
 
    }
 
    //后置通知
//    @After("log()")
    public void after(JoinPoint jp){
        log.info(jp.getSignature().getDeclaringTypeName()+"类的"+jp.getSignature().getName()+  "方法after日志");
    }
 
    //返回通知
//    @AfterReturning("log()")
    public void afterRet(JoinPoint jp){
        log.info(jp.getSignature().getDeclaringTypeName()+"类的"+jp.getSignature().getName()+ "方法AfterReturning日志");
    }
 
    //异常通知
//    @AfterThrowing("log()")
    public void afterError(JoinPoint jp){
        log.info(jp.getSignature().getDeclaringTypeName()+"类的"+jp.getSignature().getName()+ "方法AfterThrowing日志");
    }
}
 