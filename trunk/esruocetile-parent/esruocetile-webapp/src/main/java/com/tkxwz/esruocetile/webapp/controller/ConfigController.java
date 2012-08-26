package com.tkxwz.esruocetile.webapp.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.core.util.BeanUtil;
import com.tkxwz.esruocetile.core.util.PageUtil;
import com.tkxwz.esruocetile.webapp.entity.Config;
import com.tkxwz.esruocetile.webapp.service.ConfigService;
import com.tkxwz.esruocetile.webapp.service.IndexService;

/**
 * @author Po Kong
 * @since 2012-10-26 下午12:59:38
 */
@Controller("/config.do")
public class ConfigController {

	@Autowired
	private ConfigService configService;
	
	@Autowired
	private IndexService indexService;

	@RequestMapping(params = "action=listConfig")
	public String listConfig(HttpServletRequest request, String currentPageNum) {
		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));
		this.configService.listConfig(page);
		request.setAttribute("page", page);
		return "/config/listConfig.jsp";
	}

	@RequestMapping(params = "action=toUpdateConfig")
	public String toUpdateConfig(HttpServletRequest request, String id) {
		Map map = this.configService.getConfigById(id);
		request.setAttribute("map", map);
		return "/config/updateConfig.jsp";
	}

	@RequestMapping(params = "action=updateConfig")
	public String updateConfig(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException {
		Config config = new Config();
		BeanUtil.populate(config, request.getParameterMap());
		
		int result = this.configService.updateConfig(config);
		return "redirect:config.do?action=listConfig";
	}

	@RequestMapping(params = "action=viewConfig")
	public String viewConfig(HttpServletRequest request, String code) {
		this.indexService.indexSessionData(request);
		Map map = this.configService.getConfigByCode(code);
		request.setAttribute("map", map);

		return "/front/config/viewConfig.jsp";
	}

}
