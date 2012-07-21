package com.tkxwz.esruocetile.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.core.util.PageUtil;
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
	 * display the user list paged
	 * 
	 * @author Po Kong
	 * @since 2012-7-20 下午11:52:43
	 * @param request
	 * @param currentPageNum
	 * @return the page to display the user list
	 */

	@RequestMapping(params = "action=listUser")
	public String listUser(HttpServletRequest request, String currentPageNum) {
		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));
		this.userService.listUser(page);
		request.setAttribute("page", page);
		System.out.println(page);
		return "/user/list.jsp";
	}
}
