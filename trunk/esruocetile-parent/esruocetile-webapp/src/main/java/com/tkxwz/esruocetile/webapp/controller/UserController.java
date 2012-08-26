package com.tkxwz.esruocetile.webapp.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.core.util.MD5Util;
import com.tkxwz.esruocetile.core.util.PageUtil;
import com.tkxwz.esruocetile.webapp.entity.User;
import com.tkxwz.esruocetile.webapp.service.UserService;

/**
 * @author Po Kong
 * @since 2012-5-20 上午11:21:39
 */
@Controller(value = "/user.do")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 12:49:38
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listUser")
	public String listUser(HttpServletRequest request, String currentPageNum) {
		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));
		this.userService.listUser(page);
		request.setAttribute("page", page);
		return "/user/listUser.jsp";
	}

	/**
	 * to add a user
	 * 
	 * @author Po Kong
	 * @since 21 Jul 2012 12:41:27
	 * @return the page to add user
	 */

	@RequestMapping(params = "action=toAddUser")
	public String toAddUser() {
		return "/user/addUser.jsp";
	}

	/**
	 * add a user and then forward to the list page
	 * 
	 * @author Po Kong
	 * @since 21 Jul 2012 12:48:53
	 * @return the page to list the users
	 */
	@RequestMapping(params = "action=addUser")
	public String addUser(String name, String password, String rePassword) {
		User user = new User();
		user.setName(name);
		user.setPassword(MD5Util.MD5(password));

		int result = this.userService.addUser(user);
		return "redirect:user.do?action=listUser";
	}

	@RequestMapping(params = "action=deleteUser")
	public String deleteUser(HttpServletRequest request,
			HttpServletResponse response, String id) throws IOException {
		String[] ids = id.split(",");
		int result = this.userService.batchDeleteUser(ids);
		response.getWriter().write(" 成功删除 " + result + "条记录");
		return null;
	}

	@RequestMapping(params = "action=toUpdateUser")
	public String toUpdateUser(HttpServletRequest request, String id) {
		Map map = this.userService.getUserById(id);
		request.setAttribute("map", map);
		return "/user/updateUser.jsp";
	}

	@RequestMapping(params = "action=updateUser")
	public String updateUser(int id, String name, String password) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setPassword(MD5Util.MD5(password));
		int result = this.userService.updateUser(user);
		return "redirect:user.do?action=listUser";
	}

	@RequestMapping(params = "action=toUpdatePersonalPassword")
	public String toUpdatePersonalPassword() {
		return "/user/updatePersonalPassword.jsp";
	}

	@RequestMapping(params = "action=checkLastPassword")
	public String checkLastPassword(HttpServletRequest request,
			HttpServletResponse response, int id, String lastPassword)
			throws IOException {
		String result = "false";
		boolean isValidPassword = this.userService.checkLastPassword(id,
				MD5Util.MD5(lastPassword));
		if (isValidPassword) {
			result = "true";
		}
		response.getWriter().write(result);
		return null;
	}
	@RequestMapping(params = "action=isUserExist")
	public String isUserExist(HttpServletRequest request,
			HttpServletResponse response, String name)
					throws IOException {
		String result = "false";
		boolean isUserExist = this.userService.isUserExist(StringUtils.trim(name));
		if (!isUserExist) {
			result = "true";
		}
		response.getWriter().write(result);
		return null;
	}
}
