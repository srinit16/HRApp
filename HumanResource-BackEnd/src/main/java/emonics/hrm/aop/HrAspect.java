package emonics.hrm.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import emonics.hrm.entities.Employee;

@Aspect @Component
public class HrAspect {
	
	
			@Before(value="execution(* emonics.hrm.services.HrService.*(..))")
			public void createLog(JoinPoint jp) {
				System.out.println("Greeting from "+jp.getTarget());
			}
			
			
			@After(value="execution(* emonics.hrm.services.HrService.*(..))")
			public void dropLog(JoinPoint jp) {
				System.out.println("Bye from "+jp.getTarget());
			}
			
			@Around(value="execution(* emonics.hrm.services.HrService.save(..))")
			public void seeMe(ProceedingJoinPoint jp) {
				
				long intime=System.currentTimeMillis();
				Object[] args=jp.getArgs();
				if(args[0] instanceof Employee) {
					Employee e=(Employee) args[0];
					e.setName(e.getName().toUpperCase());
					System.out.println("Arg:"+args[0]);
				}
				try {
					jp.proceed();
				} catch (Throwable e) {	
					e.printStackTrace();
				}

				long outtime=System.currentTimeMillis();
				long duration=outtime-intime;
				System.out.println(duration);
			}
}
