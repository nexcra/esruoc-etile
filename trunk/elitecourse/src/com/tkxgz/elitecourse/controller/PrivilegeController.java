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

import com.tkxgz.elitecourse.bean.Privilege;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.PrivilegeService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;



/**
 * 权限Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/privilege.do")
public class PrivilegeController {

	private static final String MODULE_NAME = "权限管理";
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private PrivilegeService privilegeService;
	
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

		return this.listPrivilege(request, currentPageNum);
	}

	/**
	 * 查询权限列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listPrivilege")
	public String listPrivilege(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.privilegeService.listPrivilege(page);

		request.setAttribute("page", page);
		
		

		return "/admin/privilege/listPrivilege.jsp";
	}

	/**
	 * 转到新增权限页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddPrivilege")
	public String toAddPrivilege(HttpServletRequest request) {
		
		
		
		return "/admin/privilege/addPrivilege.jsp";
	}

	/**
	 * 新增权限
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addPrivilege")
	public String addPrivilege(HttpServletRequest request) throws Exception {
		
		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/privilege.do?action=listPrivilege";

		// 获取表单参数
		Privilege privilege = new Privilege();
		BeanUtil.populate(privilege, request.getParameterMap());
		
		
		// 设置操作管理员
privilege.setCreateUserId(admin.getId());privilege.setCreateUserName(admin.getName());

		int affectedRows = this.privilegeService.addPrivilege(privilege);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddPrivilege(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加权限");

		this.logService.addLog(log);
		
		return result;
	}

	/**
	 * 删除权限
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deletePrivilege")
	public String deletePrivilege(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.privilegeService.batchDeletePrivilege(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");
		
	
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除权限");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改权限页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdatePrivilege")
	public String toUpdatePrivilege(HttpServletRequest request, String id) {

		Map map = this.privilegeService.getPrivilegeById(id);

		request.setAttribute("map", map);
		
		

		return "/admin/privilege/updatePrivilege.jsp";
	}

	/**
	 * 修改权限
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
	@RequestMapping(params = "action=updatePrivilege")
	public String updatePrivilege(HttpServletRequest request, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/privilege.do?action=listPrivilege";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		Privilege privilege = this.privilegeService.findPrivilegeById(id);
		BeanUtil.populate(privilege, request.getParameterMap());

		
		
		int affectedRows = this.privilegeService.updatePrivilege(privilege);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddPrivilege(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改权限");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看权限
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewPrivilege")
	public String viewPrivilege(HttpServletRequest request, String id) {

		Map map = this.privilegeService.getPrivilegeById(id);

		request.setAttribute("map", map);

		return "/admin/privilege/viewPrivilege.jsp";
	}

	/**
	 * 分页搜索权限列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchPrivilege")
	public String searchPrivilege(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		Privilege privilege = new Privilege();
		BeanUtil.populate(privilege, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.privilegeService.searchPrivilege(page, privilege);
		
		
		
		request.setAttribute("page", page);
		request.setAttribute("bean", privilege);

		return "/admin/privilege/listPrivilege.jsp";
	}
	
	/**
	 * 导出权限数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportPrivilegeList")
	public String exportPrivilegeList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {
			
		// 查询需要权限的权限列表
		Privilege privilege = new Privilege();
		BeanUtil.populate(privilege, request.getParameterMap());
		List<Privilege> excelContent = this.privilegeService.searchPrivilegeForList(privilege);

		String[] columnNames = new String[] { "PrivilegeCode" ,"Name" ,"Status" ,"CreateUserName" ,"CreateUserId" ,"CreateTime" ,"Remark" };

		String titleName = "privilegeList.xls";// 不创建说明;
		String sheetName = "权限列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.privilegeService.exportPrivilegeList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}
	

}
