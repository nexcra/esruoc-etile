package com.tkxwz.esruocetile.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Po Kong
 * @since 2012-8-4 下午5:44:34
 */
@Controller(value = "/message.do")
public class MessageController {

	@RequestMapping()
	public String defaultPage(HttpServletRequest request, String message) {
		return this.toMessage(request, message);
	}

	@RequestMapping(params = "action=showMessage")
	public String toMessage(HttpServletRequest request, String message) {
		return "/front/message.jsp";
	}
}
