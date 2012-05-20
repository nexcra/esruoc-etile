package com.tkxwz.esruocetile.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxwz.esruocetile.webapp.entity.User;
import com.tkxwz.esruocetile.webapp.service.UserService;

/**
 * @author 孔沛洪
 * @since 2012-5-20 上午11:21:39
 */
@Controller(value = "/user.html")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping()
	public String queryUserDetail() {
		User user = this.userService.getUserDetail(1);
		System.out.println(user.getName());
		return null;
	}
}
