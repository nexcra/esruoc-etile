package com.tkxgz.elitecourse.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import com.tkxgz.elitecourse.core.constant.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tkxgz.elitecourse.bean.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxgz.elitecourse.bean.Template;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.TemplateService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;

/**
 * 模板Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/template.do")
public class TemplateController {

	private static final String MODULE_NAME = "模板管理";

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LogService logService;

	@Autowired
	private TemplateService templateService;

	@Autowired
	private UserService userService;

	/**
	 * 默认action
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping
	public String defaultAction(HttpServletRequest request,
			String currentPageNum) {

		return this.listTemplate(request, currentPageNum);
	}

	/**
	 * 查询模板列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listTemplate")
	public String listTemplate(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.templateService.listTemplate(page);

		request.setAttribute("page", page);

		return "/admin/template/listTemplate.jsp";
	}

	/**
	 * 转到新增模板页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddTemplate")
	public String toAddTemplate(HttpServletRequest request) {

		return "/admin/template/addTemplate.jsp";
	}

	/**
	 * 新增模板
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addTemplate")
	public String addTemplate(HttpServletRequest request) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/template.do?action=listTemplate";

		// 获取表单参数
		Template template = new Template();
		BeanUtil.populate(template, request.getParameterMap());

		// 设置操作管理员
		template.setCreateUserId(admin.getId());
		template.setCreateUserName(admin.getName());

		int affectedRows = this.templateService.addTemplate(template);// 新增

		String filePath = "E:\\JavaWorkSpaces\\eclipse3.7_newFramework\\elitecourse\\" +
				"WebContent\\pages\\template\\default\\index.jsp";
		String content = template.getTemplateContent();
		this.templateService.writeSource(filePath, content);
		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddTemplate(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加模板");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 删除模板
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteTemplate")
	public String deleteTemplate(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.templateService.batchDeleteTemplate(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除模板");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改模板页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateTemplate")
	public String toUpdateTemplate(HttpServletRequest request, String id) {

		Map map = this.templateService.getTemplateById(id);

		request.setAttribute("map", map);

		return "/admin/template/updateTemplate.jsp";
	}

	/**
	 * 修改模板
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @param name
	 * @param password
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(params = "action=updateTemplate")
	public String updateTemplate(HttpServletRequest request, String id)
			throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/template.do?action=listTemplate";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		Template template = this.templateService.findTemplateById(id);
		BeanUtil.populate(template, request.getParameterMap());

		int affectedRows = this.templateService.updateTemplate(template);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddTemplate(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改模板");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看模板
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewTemplate")
	public String viewTemplate(HttpServletRequest request, String id) {

		Map map = this.templateService.getTemplateById(id);

		request.setAttribute("map", map);

		return "/admin/template/viewTemplate.jsp";
	}

	/**
	 * 分页搜索模板列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchTemplate")
	public String searchTemplate(HttpServletRequest request,
			String currentPageNum) throws IllegalAccessException,
			InvocationTargetException {

		Template template = new Template();
		BeanUtil.populate(template, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.templateService.searchTemplate(page, template);

		request.setAttribute("page", page);
		request.setAttribute("bean", template);

		return "/admin/template/listTemplate.jsp";
	}

	/**
	 * 导出模板数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportTemplateList")
	public String exportTemplateList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {

		// 查询需要模板的模板列表
		Template template = new Template();
		BeanUtil.populate(template, request.getParameterMap());
		List<Template> excelContent = this.templateService
				.searchTemplateForList(template);

		String[] columnNames = new String[] { "TemplateName", "Description",
				"CreateUserId", "CreateUserName", "CreateTime", "FileName",
				"TemplateCode", "TemplateContent" };

		String titleName = "templateList.xls";// 不创建说明;
		String sheetName = "模板列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.templateService.exportTemplateList(request, excelContent,
				columnNames, sheetName, outputStream);

		return null;
	}

}
