package com.user.userapi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component

//log inputs and outputs of each call 
public class LogAspect {
	
	@Before("execution(* com.user.userapi.service.implementation.UserServiceImpl.*(..))")
	public void beforeAdv(JoinPoint joinPoint) {  
		System.out.println("Before method:" + joinPoint.getSignature());  
		Object[] o = joinPoint.getArgs();
		for(int i=0;i<o.length; i++) {
			System.out.println(o[i].toString()); 
		}

	}  	
	
//input
	@Before("execution(* com.user.userapi.rest.UserController.*(..))")
	public void beforeC(JoinPoint joinPoint) {  
		System.out.println("Before method:" + joinPoint.getSignature());  
		Object[] o = joinPoint.getArgs();
		for(int i=0;i<o.length; i++) {
			System.out.println(o[i].toString()); 
		}

	} 
	
	//output
	@AfterReturning(pointcut = "execution(* com.user.userapi.rest.UserController.*(..))", returning = "result")
	public void afterR(JoinPoint joinPoint, Object result) {
		System.out.println("After method:" + joinPoint.getSignature()); 
		System.out.println(result); 
		
	}
	
	@AfterReturning(pointcut = "execution(* com.user.userapi.service.implementation.UserServiceImpl.*(..))", returning = "result")
	public void afterS(JoinPoint joinPoint, Object result) {
		System.out.println("After method:" + joinPoint.getSignature()); 
		System.out.println(result); 
		
	}
	
	
	//temps d'execution
	
	 @Around("execution(* com.user.userapi.rest.UserController.*(..))")
	   public Object trace(ProceedingJoinPoint pjp) throws Throwable {
	      long t1 = System.currentTimeMillis();

	      Object value = pjp.proceed();

	      long t2 = System.currentTimeMillis();

	      System.out.println("Executed " + pjp.getSignature() + " in " + (t2 - t1) + "ms");

	      return value;
	   }
	
	
}
