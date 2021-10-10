package sample.data.jpa.monitor;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitorRdv {
	@AfterReturning("execution(* sample.data.jpa.web.RendezVousController.*(..))")
	public void logServiceAccess(JoinPoint joinPoint) {
		//System.out.println("Completed task for rdv: " + joinPoint.toString().replace("execution(List sample.data.jpa.web.RendezVousController.", ""));
		Logger.global.info("Completed task for rdv: " + joinPoint.toString().replace("execution(List sample.data.jpa.web.RendezVousController.", ""));
	}
}
