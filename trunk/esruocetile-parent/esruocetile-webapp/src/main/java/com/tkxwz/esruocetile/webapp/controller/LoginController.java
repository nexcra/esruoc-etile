package com.tkxwz.esruocetile.webapp.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxwz.esruocetile.webapp.entity.User;
import com.tkxwz.esruocetile.webapp.service.LoginService;

/**
 * @author Po Kong
 * @since 22 Jul 2012 11:23:54
 */
@Controller(value = "/login.do")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(params = "action=toLogin")
	public String toAdminLogin() {
		return "/login.jsp";
	}

	@RequestMapping(params = "action=isCheckCodeValid")
	public String isCheckCodeValid(HttpServletRequest request,
			HttpServletResponse response, String checkCode) throws IOException {
		String result = "true";
		String checkCodeInSession = (String) request.getSession().getAttribute(
				"checkCodeInSession");
		// 验证码错误
		if (StringUtils.isNotEmpty(checkCode)
				&& StringUtils.isNotEmpty(checkCodeInSession)
				&& !checkCode.equalsIgnoreCase(checkCodeInSession)) {
			result = "false";
		}
		response.getWriter().write(result);
		return null;
	}

	@RequestMapping(params = "action=processLogin")
	public String processLogin(HttpServletRequest request,
			HttpServletResponse response, String name, String password,
			String checkCode) {
		String returnPage = "redirect:/main.do";

		// 验证用户密码是否匹配
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		int userCount = this.loginService.getUserCountByNameAndPassword(user);
		boolean isUserExist = this.loginService.isUserExist(userCount);

		if (!isUserExist) {// 用户不存在
			request.setAttribute("error", "用户名或者密码错误, 请重新登录!");
			returnPage = "/login.jsp";
		} else {// 用户存在
			Map userMap = this.loginService.getUserByUserAndPassword(user);
			HttpSession session = request.getSession();
			session.setAttribute("adminId", userMap.get("id"));
			session.setAttribute("adminName", userMap.get("name"));
			session.setAttribute("adminPassword", userMap.get("password"));
		}

		return returnPage;
	}

	@RequestMapping(params = "action=logoutForAdmin")
	public String logoutForAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("adminId");
		session.removeAttribute("adminName");

		return "redirect:/login.do?action=toLogin";
	}
}
