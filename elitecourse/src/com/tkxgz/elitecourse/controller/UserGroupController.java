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

import com.tkxgz.elitecourse.bean.UserGroup;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.UserGroupService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;
import com.tkxgz.elitecourse.bean.Group;import com.tkxgz.elitecourse.service.GroupService;


/**
 * 用户组Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/userGroup.do")
public class UserGroupController {

	private static final String MODULE_NAME = "用户组管理";
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private UserGroupService userGroupService;
	
	@Autowired
private UserService userService;


	 @Autowired private GroupService groupService;

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

		return this.listUserGroup(request, currentPageNum);
	}

	/**
	 * 查询用户组列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listUserGroup")
	public String listUserGroup(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.userGroupService.listUserGroup(page);

		request.setAttribute("page", page);
		
		List<Group> groupList = this.groupService.listAllGroup();request.setAttribute("groupList", groupList);

		return "/admin/userGroup/listUserGroup.jsp";
	}

	/**
	 * 转到新增用户组页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddUserGroup")
	public String toAddUserGroup(HttpServletRequest request) {
		
		List<Group> groupList = this.groupService.listAllGroup();request.setAttribute("groupList", groupList);
		
		return "/admin/userGroup/addUserGroup.jsp";
	}

	/**
	 * 新增用户组
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addUserGroup")
	public String addUserGroup(HttpServletRequest request) throws Exception {
		
		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/userGroup.do?action=listUserGroup";

		// 获取表单参数
		UserGroup userGroup = new UserGroup();
		BeanUtil.populate(userGroup, request.getParameterMap());
		
		
		

		int affectedRows = this.userGroupService.addUserGroup(userGroup);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddUserGroup(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加用户组");

		this.logService.addLog(log);
		
		return result;
	}

	/**
	 * 删除用户组
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteUserGroup")
	public String deleteUserGroup(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.userGroupService.batchDeleteUserGroup(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");
		
	
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除用户组");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改用户组页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateUserGroup")
	public String toUpdateUserGroup(HttpServletRequest request, String id) {

		Map map = this.userGroupService.getUserGroupById(id);

		request.setAttribute("map", map);
		
		List<Group> groupList = this.groupService.listAllGroup();request.setAttribute("groupList", groupList);

		return "/admin/userGroup/updateUserGroup.jsp";
	}

	/**
	 * 修改用户组
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
	@RequestMapping(params = "action=updateUserGroup")
	public String updateUserGroup(HttpServletRequest request, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/userGroup.do?action=listUserGroup";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		UserGroup userGroup = this.userGroupService.findUserGroupById(id);
		BeanUtil.populate(userGroup, request.getParameterMap());

		
		
		int affectedRows = this.userGroupService.updateUserGroup(userGroup);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddUserGroup(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改用户组");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看用户组
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewUserGroup")
	public String viewUserGroup(HttpServletRequest request, String id) {

		Map map = this.userGroupService.getUserGroupById(id);

		request.setAttribute("map", map);

		return "/admin/userGroup/viewUserGroup.jsp";
	}

	/**
	 * 分页搜索用户组列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchUserGroup")
	public String searchUserGroup(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		UserGroup userGroup = new UserGroup();
		BeanUtil.populate(userGroup, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.userGroupService.searchUserGroup(page, userGroup);
		
		List<Group> groupList = this.groupService.listAllGroup();request.setAttribute("groupList", groupList);
		
		request.setAttribute("page", page);
		request.setAttribute("bean", userGroup);

		return "/admin/userGroup/listUserGroup.jsp";
	}
	
	/**
	 * 导出用户组数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportUserGroupList")
	public String exportUserGroupList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {
			
		// 查询需要用户组的用户组列表
		UserGroup userGroup = new UserGroup();
		BeanUtil.populate(userGroup, request.getParameterMap());
		List<UserGroup> excelContent = this.userGroupService.searchUserGroupForList(userGroup);

		String[] columnNames = new String[] { "UserId" ,"GroupId" };

		String titleName = "userGroupList.xls";// 不创建说明;
		String sheetName = "用户组列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.userGroupService.exportUserGroupList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}
	

}
