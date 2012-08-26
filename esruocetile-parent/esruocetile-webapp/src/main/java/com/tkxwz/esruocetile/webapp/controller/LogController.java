package com.tkxwz.esruocetile.webapp.controller;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Po Kong
 * @since 2012-8-16 下午10:18:14
 */
@Aspect
public class LogController {

	@Pointcut("execution (* com.tkxwz.esruocetile.webapp.service.IndexService.indexSessionData(..))")
	private void anyMethod() {
	}

	@Before("anyMethod()")
	public void doAccessCheck() {
		System.out.println("前置通知");
	}
}
