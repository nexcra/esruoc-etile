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

import com.tkxgz.elitecourse.bean.GroupPrivilege;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.GroupPrivilegeService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;
import com.tkxgz.elitecourse.bean.Group;import com.tkxgz.elitecourse.service.GroupService;import com.tkxgz.elitecourse.bean.Privilege;import com.tkxgz.elitecourse.service.PrivilegeService;


/**
 * 组权限关联Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/groupPrivilege.do")
public class GroupPrivilegeController {

	private static final String MODULE_NAME = "组权限关联管理";
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private GroupPrivilegeService groupPrivilegeService;
	
	@Autowired
private UserService userService;


	 @Autowired private GroupService groupService; @Autowired private PrivilegeService privilegeService;

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

		return this.listGroupPrivilege(request, currentPageNum);
	}

	/**
	 * 查询组权限关联列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listGroupPrivilege")
	public String listGroupPrivilege(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.groupPrivilegeService.listGroupPrivilege(page);

		request.setAttribute("page", page);
		
		List<Group> groupList = this.groupService.listAllGroup();request.setAttribute("groupList", groupList);List<Privilege> privilegeList = this.privilegeService.listAllPrivilege();request.setAttribute("privilegeList", privilegeList);

		return "/admin/groupPrivilege/listGroupPrivilege.jsp";
	}

	/**
	 * 转到新增组权限关联页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddGroupPrivilege")
	public String toAddGroupPrivilege(HttpServletRequest request) {
		
		List<Group> groupList = this.groupService.listAllGroup();request.setAttribute("groupList", groupList);List<Privilege> privilegeList = this.privilegeService.listAllPrivilege();request.setAttribute("privilegeList", privilegeList);
		
		return "/admin/groupPrivilege/addGroupPrivilege.jsp";
	}

	/**
	 * 新增组权限关联
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addGroupPrivilege")
	public String addGroupPrivilege(HttpServletRequest request) throws Exception {
		
		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/groupPrivilege.do?action=listGroupPrivilege";

		// 获取表单参数
		GroupPrivilege groupPrivilege = new GroupPrivilege();
		BeanUtil.populate(groupPrivilege, request.getParameterMap());
		
		
		

		int affectedRows = this.groupPrivilegeService.addGroupPrivilege(groupPrivilege);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddGroupPrivilege(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加组权限关联");

		this.logService.addLog(log);
		
		return result;
	}

	/**
	 * 删除组权限关联
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteGroupPrivilege")
	public String deleteGroupPrivilege(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.groupPrivilegeService.batchDeleteGroupPrivilege(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");
		
	
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除组权限关联");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改组权限关联页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateGroupPrivilege")
	public String toUpdateGroupPrivilege(HttpServletRequest request, String id) {

		Map map = this.groupPrivilegeService.getGroupPrivilegeById(id);

		request.setAttribute("map", map);
		
		List<Group> groupList = this.groupService.listAllGroup();request.setAttribute("groupList", groupList);List<Privilege> privilegeList = this.privilegeService.listAllPrivilege();request.setAttribute("privilegeList", privilegeList);

		return "/admin/groupPrivilege/updateGroupPrivilege.jsp";
	}

	/**
	 * 修改组权限关联
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
	@RequestMapping(params = "action=updateGroupPrivilege")
	public String updateGroupPrivilege(HttpServletRequest request, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/groupPrivilege.do?action=listGroupPrivilege";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		GroupPrivilege groupPrivilege = this.groupPrivilegeService.findGroupPrivilegeById(id);
		BeanUtil.populate(groupPrivilege, request.getParameterMap());

		
		
		int affectedRows = this.groupPrivilegeService.updateGroupPrivilege(groupPrivilege);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddGroupPrivilege(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改组权限关联");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看组权限关联
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewGroupPrivilege")
	public String viewGroupPrivilege(HttpServletRequest request, String id) {

		Map map = this.groupPrivilegeService.getGroupPrivilegeById(id);

		request.setAttribute("map", map);

		return "/admin/groupPrivilege/viewGroupPrivilege.jsp";
	}

	/**
	 * 分页搜索组权限关联列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchGroupPrivilege")
	public String searchGroupPrivilege(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		GroupPrivilege groupPrivilege = new GroupPrivilege();
		BeanUtil.populate(groupPrivilege, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.groupPrivilegeService.searchGroupPrivilege(page, groupPrivilege);
		
		List<Group> groupList = this.groupService.listAllGroup();request.setAttribute("groupList", groupList);List<Privilege> privilegeList = this.privilegeService.listAllPrivilege();request.setAttribute("privilegeList", privilegeList);
		
		request.setAttribute("page", page);
		request.setAttribute("bean", groupPrivilege);

		return "/admin/groupPrivilege/listGroupPrivilege.jsp";
	}
	
	/**
	 * 导出组权限关联数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportGroupPrivilegeList")
	public String exportGroupPrivilegeList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {
			
		// 查询需要组权限关联的组权限关联列表
		GroupPrivilege groupPrivilege = new GroupPrivilege();
		BeanUtil.populate(groupPrivilege, request.getParameterMap());
		List<GroupPrivilege> excelContent = this.groupPrivilegeService.searchGroupPrivilegeForList(groupPrivilege);

		String[] columnNames = new String[] { "GroupId" ,"PrivilegeId" };

		String titleName = "groupPrivilegeList.xls";// 不创建说明;
		String sheetName = "组权限关联列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.groupPrivilegeService.exportGroupPrivilegeList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}
	

}
