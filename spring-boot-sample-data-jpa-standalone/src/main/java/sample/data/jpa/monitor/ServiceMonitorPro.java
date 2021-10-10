package sample.data.jpa.monitor;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitorPro {
		@AfterReturning("execution(* sample.data.jpa.web.ProfessionnelController.*(..))")
		public void logServiceAccess(JoinPoint joinPoint) {
			Logger.global.info("Completed task for professionnel: " + joinPoint.toString().replace("execution(List sample.data.jpa.web.ProfessionnelController.", ""));
		}
}
