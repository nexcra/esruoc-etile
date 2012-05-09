package com.tkxwz.esruocetile.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 孔沛洪
 * @since 2012-5-9 下午5:26:14
 */
@Controller(value = "/index.html")
public class IndexController {

	@RequestMapping()
	public String index() {
		return "index";
	}
}
