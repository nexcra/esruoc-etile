package com.tkxgz.elitecourse.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxgz.elitecourse.bean.Log;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.core.constant.CommonConstants;
import com.tkxgz.elitecourse.core.util.MD5Util;
import com.tkxgz.elitecourse.service.LogService;
import com.tkxgz.elitecourse.service.LoginService;
import com.tkxgz.elitecourse.service.UserService;

/**
 * @author Po Kong
 * @since 22 Jul 2012 11:23:54
 */
@Controller(value = "/login.do")
public class LoginController {

	private static final String MODULE_NAME = "登录";

	private boolean needCheckCode = false;

	@Autowired
	private LogService logService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserService userService;

	@RequestMapping(params = "action=toLogin")
	public String toAdminLogin() {
		return "/login.jsp";
	}

	/**
	 * 验证码是否正确
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param checkCode
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params = "action=isCheckCodeValid")
	public String isCheckCodeValid(HttpServletRequest request,
			HttpServletResponse response, String checkCode) throws IOException {
		String result = "true";
		if (needCheckCode) {
			String checkCodeInSession = (String) request.getSession()
					.getAttribute("checkCodeInSession");
			// 验证码错误
			if (StringUtils.isNotEmpty(checkCode)
					&& StringUtils.isNotEmpty(checkCodeInSession)
					&& !checkCode.equalsIgnoreCase(checkCodeInSession)) {
				result = "false";
			}
		}
		response.getWriter().write(result);
		return null;
	}

	/**
	 * 处理用户登录
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param loginType
	 * @param name
	 * @param password
	 * @param checkCode
	 * @return
	 */
	@RequestMapping(params = "action=processLogin")
	public String processLogin(HttpServletRequest request,
			HttpServletResponse response, String loginType, String name,
			String password, String checkCode) {
		String returnPage = "redirect:/main.do";
		String checkCodeInSession = null;
		boolean checkCodeRight = false;
		if (needCheckCode) {

			// 验证码
			checkCodeInSession = (String) request.getSession().getAttribute(
					"checkCodeInSession");
			checkCodeRight = !checkCode.equalsIgnoreCase(checkCodeInSession);

			// 验证码错误
			if (needCheckCode && StringUtils.isEmpty(checkCode)
					|| StringUtils.isEmpty(checkCodeInSession)
					|| checkCodeRight) {
				request.setAttribute("error", "验证码错误，请重新输入!");
				returnPage = "/login.jsp";
			}
		}

		returnPage = processAdminLogin(request, name, password);

		return returnPage;
	}

	@RequestMapping(params = "action=studentLogin")
	public String studentLogin(HttpServletRequest request,
			HttpServletResponse response, String loginType, String name,
			String password, String checkCode) {
		String returnPage = "redirect:/main.do";
		String checkCodeInSession = null;
		boolean checkCodeRight = false;
		if (needCheckCode) {

			// 验证码
			checkCodeInSession = (String) request.getSession().getAttribute(
					"checkCodeInSession");
			checkCodeRight = !checkCode.equalsIgnoreCase(checkCodeInSession);

			// 验证码错误
			if (needCheckCode && StringUtils.isEmpty(checkCode)
					|| StringUtils.isEmpty(checkCodeInSession)
					|| checkCodeRight) {
				request.setAttribute("error", "验证码错误，请重新输入!");
				returnPage = "/login.jsp";
			}
		}

		// 学生登录
		returnPage = processStudentLogin(request, name, password);

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
		String returnPage = "redirect:/front/index.do";
		password = MD5Util.MD5(password);
		// 验证用户密码是否匹配

		// int studentCount = this.loginService.getStudentCountByNameAndPassword(
		// name, password);
		int studentCount = this.loginService.getUserCountByNameAndPassword(
				name, password);
		boolean isUserExist = this.loginService.isUserExist(studentCount);

		if (!isUserExist) {// 用户不存在
			request.setAttribute("error", "用户名或者密码错误, 请重新登录!请确认用户是否存在");
			returnPage = "/login.jsp";
		} else {
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			Map userMap = this.loginService.getUserByUserAndPassword(user);
			HttpSession session = request.getSession();

			User student = this.loginService.findUserByName(name);
			session.setAttribute("student", student);

			Log log = new Log();
			log.setModule(MODULE_NAME);
			log.setUserId(student.getId());
			log.setUserName(student.getName());
			log.setLogType(CommonConstants.LOG_TYPE_LOGIN);
			log.setIpAddress(request.getRemoteAddr());
			log.setDescription("登录系统");

			this.logService.addLog(log);
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
		user.setPassword(MD5Util.MD5(password));
		int userCount = this.loginService.getUserCountByNameAndPassword(user);
		boolean isUserExist = this.loginService.isUserExist(userCount);

		if (!isUserExist) {// 用户不存在
			request.setAttribute("message", "用户名或者密码错误, 请重新登录!");
			returnPage = "/login.jsp";
		} else {// 用户存在
			HttpSession session = request.getSession();

			User loginUser = this.loginService.findUserByName(name);

			Log log = new Log();
			log.setModule(MODULE_NAME);
			log.setUserId(loginUser.getId());
			log.setUserName(loginUser.getName());
			log.setLogType(CommonConstants.LOG_TYPE_LOGIN);
			log.setIpAddress(request.getRemoteAddr());
			log.setDescription("登录系统");

			this.logService.addLog(log);

			if ("manager".equals(loginUser.getIsAdmin())) {
				session.setAttribute("admin", loginUser);
			} else {
				session.setAttribute("student", loginUser);
				returnPage = "redirect:/front/index.do"; // 如果是学生，则是转向学生页面
			}
		}
		return returnPage;
	}

	@RequestMapping(params = "action=logoutForAdmin")
	public String logoutForAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();

		// 获取操作管理员信息
		// User admin = (User) request.getSession().getAttribute("admin");
		// admin = this.userService.findUserById(admin.getId());
		//
		// Log log = new Log();
		// log.setModule(MODULE_NAME);
		// log.setUserId(admin.getId());
		// log.setUserName(admin.getName());
		// log.setLogType(CommonConstants.LOG_TYPE_LOGOUT);
		// log.setIpAddress(request.getRemoteAddr());
		// log.setDescription("登出系统");
		//
		// session.removeAttribute("adminId");
		// session.removeAttribute("adminName");

		// this.logService.addLog(log);
		return "redirect:/login.do?action=toLogin";
	}

	@RequestMapping(params = "action=logoutForStudent")
	public String logoutForStudent(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("student");

		return "redirect:/front/index.do";
	}
}
