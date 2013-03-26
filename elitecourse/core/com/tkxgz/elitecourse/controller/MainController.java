package com.tkxgz.elitecourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Po Kong
 * @since 22 Jul 2012 22:49:09
 */
@Controller(value = "/main.do")
public class MainController {

	@RequestMapping()
	public String toMainPage() {
		return "/main.jsp";
	}

	@RequestMapping(params = "action=toWelcome")
	public String toWelcome() {
		return "/welcome.jsp";
	}
}
