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

import com.tkxgz.elitecourse.bean.Group;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.GroupService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;



/**
 * 组Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/group.do")
public class GroupController {

	private static final String MODULE_NAME = "组管理";
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private GroupService groupService;
	
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

		return this.listGroup(request, currentPageNum);
	}

	/**
	 * 查询组列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listGroup")
	public String listGroup(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.groupService.listGroup(page);

		request.setAttribute("page", page);
		
		

		return "/admin/group/listGroup.jsp";
	}

	/**
	 * 转到新增组页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddGroup")
	public String toAddGroup(HttpServletRequest request) {
		
		
		
		return "/admin/group/addGroup.jsp";
	}

	/**
	 * 新增组
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addGroup")
	public String addGroup(HttpServletRequest request) throws Exception {
		
		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/group.do?action=listGroup";

		// 获取表单参数
		Group group = new Group();
		BeanUtil.populate(group, request.getParameterMap());
		
		
		// 设置操作管理员
group.setCreateUserId(admin.getId());group.setCreateUserName(admin.getName());

		int affectedRows = this.groupService.addGroup(group);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddGroup(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加组");

		this.logService.addLog(log);
		
		return result;
	}

	/**
	 * 删除组
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteGroup")
	public String deleteGroup(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.groupService.batchDeleteGroup(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");
		
	
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除组");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改组页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateGroup")
	public String toUpdateGroup(HttpServletRequest request, String id) {

		Map map = this.groupService.getGroupById(id);

		request.setAttribute("map", map);
		
		

		return "/admin/group/updateGroup.jsp";
	}

	/**
	 * 修改组
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
	@RequestMapping(params = "action=updateGroup")
	public String updateGroup(HttpServletRequest request, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/group.do?action=listGroup";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		Group group = this.groupService.findGroupById(id);
		BeanUtil.populate(group, request.getParameterMap());

		
		
		int affectedRows = this.groupService.updateGroup(group);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddGroup(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改组");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看组
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewGroup")
	public String viewGroup(HttpServletRequest request, String id) {

		Map map = this.groupService.getGroupById(id);

		request.setAttribute("map", map);

		return "/admin/group/viewGroup.jsp";
	}

	/**
	 * 分页搜索组列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchGroup")
	public String searchGroup(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		Group group = new Group();
		BeanUtil.populate(group, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.groupService.searchGroup(page, group);
		
		
		
		request.setAttribute("page", page);
		request.setAttribute("bean", group);

		return "/admin/group/listGroup.jsp";
	}
	
	/**
	 * 导出组数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportGroupList")
	public String exportGroupList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {
			
		// 查询需要组的组列表
		Group group = new Group();
		BeanUtil.populate(group, request.getParameterMap());
		List<Group> excelContent = this.groupService.searchGroupForList(group);

		String[] columnNames = new String[] { "Name" ,"OrderNumber" ,"CreateUserName" ,"CreateUserId" ,"CreateTime" ,"Remark" };

		String titleName = "groupList.xls";// 不创建说明;
		String sheetName = "组列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.groupService.exportGroupList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}
	

}
