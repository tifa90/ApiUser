package com.user.userapi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	
	Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Before("execution(* com.user.userapi.service.implementation.UserServiceImpl.*(..))")
	public void beforeAdv(JoinPoint joinPoint) {  
		logger.info("Before method:" + joinPoint.getSignature());  
		Object[] o = joinPoint.getArgs();
		for(int i=0;i<o.length; i++) {
			logger.info(o[i].toString()); 
		}

	}  	
	
//input
	@Before("execution(* com.user.userapi.rest.UserController.*(..))")
	public void beforeC(JoinPoint joinPoint) {  
		logger.info("Before method:" + joinPoint.getSignature());  
		Object[] o = joinPoint.getArgs();
		for(int i=0;i<o.length; i++) {
			logger.info(o[i].toString()); 
		}

	} 
	
	//output
	@AfterReturning(pointcut = "execution(* com.user.userapi.rest.UserController.*(..))", returning = "result")
	public void afterR(JoinPoint joinPoint, Object result) {
		logger.info("After method:" + joinPoint.getSignature()); 
		logger.info(" " + result); 
		
	}
	
	@AfterReturning(pointcut = "execution(* com.user.userapi.service.implementation.UserServiceImpl.*(..))", returning = "result")
	public void afterS(JoinPoint joinPoint, Object result) {
		logger.info("After method:" + joinPoint.getSignature()); 
		logger.info(" " +result); 
		
	}
	
	
	//temps d'execution
	
	 @Around("execution(* com.user.userapi.rest.UserController.*(..))")
	   public Object trace(ProceedingJoinPoint pjp) throws Throwable {
	      long t1 = System.currentTimeMillis();

	      Object value = pjp.proceed();

	      long t2 = System.currentTimeMillis();

	      logger.info("Executed " + pjp.getSignature() + " in " + (t2 - t1) + "ms");

	      return value;
	   }
	
	
}
