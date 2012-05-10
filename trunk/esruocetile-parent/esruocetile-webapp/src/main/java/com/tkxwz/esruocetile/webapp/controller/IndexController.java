package com.tkxwz.esruocetile.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxwz.esruocetile.webapp.service.IndexService;

/**
 * @author 孔沛洪
 * @since 2012-5-9 下午5:26:14
 */
@Controller(value = "/index.html")
public class IndexController {

	@Autowired
	private IndexService indexService;

	@RequestMapping()
	public String index() {
		String result = this.indexService.index();
		System.out.println("index执行的结果是:" + result);
		return "index";
	}
}
