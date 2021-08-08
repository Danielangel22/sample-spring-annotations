package com.example.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.example.service.MailerService;


@Aspect
@Configuration
public class ManagementAnnotations {

	private static Logger log = LoggerFactory.getLogger(ManagementAnnotations.class);

	@Autowired
	private MailerService mailerService;

	@Around(value = "@annotation(com.example.annotations.BaseMail)")
	public Object sendMailTemplate(ProceedingJoinPoint joinPoint) throws Throwable {
		var signature = joinPoint.getSignature();
		var methodSignature = (MethodSignature) signature;
		var strings = methodSignature.getParameterNames();
		var sig = joinPoint.getArgs();
		Map<String, String> request = new HashMap<>();
		for (var i = 0; i < sig.length; i++) {
			request.put(strings[i], String.valueOf(sig[i]));
		}
		Object result = joinPoint.proceed();
		var isSimpleMail = request.get("isSimpleMail") != null ? Boolean.valueOf(request.get("isSimpleMail")) : false;
		var from = request.get("from") == null ? request.get("sender") : request.get("from");
		mailerService.sendMail(request.get("to"), from, request.get("subject"), request.get("text"), isSimpleMail,
				request.get("template"));
		return result;
	}

	@Around(value = "@annotation(com.example.annotations.TrackTime)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long timeTaken = System.currentTimeMillis() - startTime;
		log.info("Time Taken by {} is {} miliseconds", joinPoint.getSignature().getName(), timeTaken);
		return result;
	}
}
