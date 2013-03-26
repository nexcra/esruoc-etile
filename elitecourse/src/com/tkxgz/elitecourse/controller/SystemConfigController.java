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

import com.tkxgz.elitecourse.bean.SystemConfig;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.SystemConfigService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;



/**
 * 系统配置Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/systemConfig.do")
public class SystemConfigController {

	private static final String MODULE_NAME = "系统配置管理";
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private SystemConfigService systemConfigService;
	
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

		return this.listSystemConfig(request, currentPageNum);
	}

	/**
	 * 查询系统配置列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listSystemConfig")
	public String listSystemConfig(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.systemConfigService.listSystemConfig(page);

		request.setAttribute("page", page);
		
		

		return "/admin/systemConfig/listSystemConfig.jsp";
	}

	/**
	 * 转到新增系统配置页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddSystemConfig")
	public String toAddSystemConfig(HttpServletRequest request) {
		
		
		
		return "/admin/systemConfig/addSystemConfig.jsp";
	}

	/**
	 * 新增系统配置
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addSystemConfig")
	public String addSystemConfig(HttpServletRequest request) throws Exception {
		
		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/systemConfig.do?action=listSystemConfig";

		// 获取表单参数
		SystemConfig systemConfig = new SystemConfig();
		BeanUtil.populate(systemConfig, request.getParameterMap());
		
		
		

		int affectedRows = this.systemConfigService.addSystemConfig(systemConfig);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddSystemConfig(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加系统配置");

		this.logService.addLog(log);
		
		return result;
	}

	/**
	 * 删除系统配置
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteSystemConfig")
	public String deleteSystemConfig(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.systemConfigService.batchDeleteSystemConfig(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");
		
	
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除系统配置");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改系统配置页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateSystemConfig")
	public String toUpdateSystemConfig(HttpServletRequest request, String id) {

		Map map = this.systemConfigService.getSystemConfigById(id);

		request.setAttribute("map", map);
		
		

		return "/admin/systemConfig/updateSystemConfig.jsp";
	}

	/**
	 * 修改系统配置
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
	@RequestMapping(params = "action=updateSystemConfig")
	public String updateSystemConfig(HttpServletRequest request, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/systemConfig.do?action=listSystemConfig";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		SystemConfig systemConfig = this.systemConfigService.findSystemConfigById(id);
		BeanUtil.populate(systemConfig, request.getParameterMap());

		
		
		int affectedRows = this.systemConfigService.updateSystemConfig(systemConfig);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddSystemConfig(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改系统配置");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看系统配置
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewSystemConfig")
	public String viewSystemConfig(HttpServletRequest request, String id) {

		Map map = this.systemConfigService.getSystemConfigById(id);

		request.setAttribute("map", map);

		return "/admin/systemConfig/viewSystemConfig.jsp";
	}

	/**
	 * 分页搜索系统配置列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchSystemConfig")
	public String searchSystemConfig(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		SystemConfig systemConfig = new SystemConfig();
		BeanUtil.populate(systemConfig, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.systemConfigService.searchSystemConfig(page, systemConfig);
		
		
		
		request.setAttribute("page", page);
		request.setAttribute("bean", systemConfig);

		return "/admin/systemConfig/listSystemConfig.jsp";
	}
	
	/**
	 * 导出系统配置数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportSystemConfigList")
	public String exportSystemConfigList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {
			
		// 查询需要系统配置的系统配置列表
		SystemConfig systemConfig = new SystemConfig();
		BeanUtil.populate(systemConfig, request.getParameterMap());
		List<SystemConfig> excelContent = this.systemConfigService.searchSystemConfigForList(systemConfig);

		String[] columnNames = new String[] { "SiteName" ,"Copyright" ,"SiteOwner" ,"ContactPhone" ,"ContactEmail" ,"Status" };

		String titleName = "systemConfigList.xls";// 不创建说明;
		String sheetName = "系统配置列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.systemConfigService.exportSystemConfigList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}
	

}
