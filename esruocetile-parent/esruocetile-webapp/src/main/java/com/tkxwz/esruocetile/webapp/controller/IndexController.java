package com.tkxwz.esruocetile.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxwz.esruocetile.webapp.service.IndexService;

/**
 * @author Po Kong
 * @since 2012-8-4 上午11:31:42
 */
@Controller(value = "/index.do")
public class IndexController {

	@Autowired
	private IndexService indexService;

	@RequestMapping()
	public String defaultPage(HttpServletRequest request) {
		return this.toIndex(request);
	}

	@RequestMapping(params = "action=toIndex")
	public String toIndex(HttpServletRequest request) {
		this.indexService.indexSessionData(request);

		return "/front/index.jsp";
	}

	/**
	 * @author Po Kong
	 * @since 2012-8-10 上午8:07:21
	 * @param request
	 */

}
