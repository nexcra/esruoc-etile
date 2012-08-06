package com.tkxwz.esruocetile.webapp.controller;

import java.io.IOException;
import java.util.List;
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
import com.tkxwz.esruocetile.webapp.service.StudentTestBookingService;

/**
 * @author Po Kong
 * @since 22 Jul 2012 11:23:54
 */
@Controller(value = "/login.do")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private StudentTestBookingService studentTestBookingService;

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
			HttpServletResponse response, String loginType, String name,
			String password, String checkCode) {
		String returnPage = "redirect:/main.do";
		// 验证码
		String checkCodeInSession = (String) request.getSession().getAttribute(
				"checkCodeInSession");
		// 验证码错误
		if (StringUtils.isEmpty(checkCode)
				|| StringUtils.isEmpty(checkCodeInSession)
				|| !checkCode.equalsIgnoreCase(checkCodeInSession)) {
			request.setAttribute("error", "验证码错误，请重新输入!");
			returnPage = "/login.jsp";
		} else {
			if ("student".equals(loginType)) {// 学生登录
				returnPage = processStudentLogin(request, name, password);
			} else {// 管理员登录
				returnPage = processAdminLogin(request, name, password);
			}
		}
		return returnPage;
	}

	/**
	 * @author Po Kong
	 * @since 29 Jul 2012 18:57:57
	 * @param request
	 * @param name
	 * @param password
	 * @return
	 */
	private String processStudentLogin(HttpServletRequest request, String name,
			String password) {
		String returnPage = "redirect:/main.do";
		// 验证用户密码是否匹配

		int studentCount = this.loginService.getStudentCountByNameAndPassword(
				name, password);
		boolean isUserExist = this.loginService.isUserExist(studentCount);

		if (!isUserExist) {// 用户不存在
			request.setAttribute("message", "用户名或者密码错误, 请重新登录!");
			returnPage = "/login.jsp";
		} else {// 用户存在
			Map userMap = this.loginService.getStudentByNameAndPassword(name,
					password);
			String studentId = String.valueOf(userMap.get("id"));
			List<Map<String, Object>> list = this.studentTestBookingService
					.getStudentTestBookingByStudentId(studentId);
			HttpSession session = request.getSession();
			session.setAttribute("bookingList", list);
			session.setAttribute("studentId", studentId);
			session.setAttribute("studentName", userMap.get("name"));
			session.setAttribute("studentNo", userMap.get("student_no"));
			session.setAttribute("studentPassword", password);
			returnPage = "redirect:/index.do";
		}
		return returnPage;
	}

	/**
	 * @author Po Kong
	 * @since 29 Jul 2012 18:55:04
	 * @param name
	 * @param password
	 */
	private String processAdminLogin(HttpServletRequest request, String name,
			String password) {
		String returnPage = "redirect:/main.do";
		// 验证用户密码是否匹配
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		int userCount = this.loginService.getUserCountByNameAndPassword(user);
		boolean isUserExist = this.loginService.isUserExist(userCount);

		if (!isUserExist) {// 用户不存在
			request.setAttribute("message", "用户名或者密码错误, 请重新登录!");
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

	@RequestMapping(params = "action=logoutForStudent")
	public String logoutForStudent(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("studentId");
		session.removeAttribute("studentName");
		session.removeAttribute("studentNo");
		session.removeAttribute("studentPassword");
		session.removeAttribute("bookingList");

		return "redirect:/index.do";
	}
}
