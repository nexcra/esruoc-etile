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

import com.tkxgz.elitecourse.bean.Friendsite;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.FriendsiteService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;



/**
 * 友情链接Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/friendsite.do")
public class FriendsiteController {

	private static final String MODULE_NAME = "友情链接管理";
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private FriendsiteService friendsiteService;
	
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

		return this.listFriendsite(request, currentPageNum);
	}

	/**
	 * 查询友情链接列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listFriendsite")
	public String listFriendsite(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.friendsiteService.listFriendsite(page);

		request.setAttribute("page", page);
		
		

		return "/admin/friendsite/listFriendsite.jsp";
	}

	/**
	 * 转到新增友情链接页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddFriendsite")
	public String toAddFriendsite(HttpServletRequest request) {
		
		
		
		return "/admin/friendsite/addFriendsite.jsp";
	}

	/**
	 * 新增友情链接
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addFriendsite")
	public String addFriendsite(HttpServletRequest request) throws Exception {
		
		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/friendsite.do?action=listFriendsite";

		// 获取表单参数
		Friendsite friendsite = new Friendsite();
		BeanUtil.populate(friendsite, request.getParameterMap());
		
		
		// 设置操作管理员
friendsite.setCreateUserId(admin.getId());friendsite.setCreateUserName(admin.getName());

		int affectedRows = this.friendsiteService.addFriendsite(friendsite);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddFriendsite(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加友情链接");

		this.logService.addLog(log);
		
		return result;
	}

	/**
	 * 删除友情链接
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteFriendsite")
	public String deleteFriendsite(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.friendsiteService.batchDeleteFriendsite(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");
		
	
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除友情链接");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改友情链接页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateFriendsite")
	public String toUpdateFriendsite(HttpServletRequest request, String id) {

		Map map = this.friendsiteService.getFriendsiteById(id);

		request.setAttribute("map", map);
		
		

		return "/admin/friendsite/updateFriendsite.jsp";
	}

	/**
	 * 修改友情链接
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
	@RequestMapping(params = "action=updateFriendsite")
	public String updateFriendsite(HttpServletRequest request, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/friendsite.do?action=listFriendsite";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		Friendsite friendsite = this.friendsiteService.findFriendsiteById(id);
		BeanUtil.populate(friendsite, request.getParameterMap());

		
		
		int affectedRows = this.friendsiteService.updateFriendsite(friendsite);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddFriendsite(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改友情链接");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看友情链接
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewFriendsite")
	public String viewFriendsite(HttpServletRequest request, String id) {

		Map map = this.friendsiteService.getFriendsiteById(id);

		request.setAttribute("map", map);

		return "/admin/friendsite/viewFriendsite.jsp";
	}

	/**
	 * 分页搜索友情链接列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchFriendsite")
	public String searchFriendsite(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		Friendsite friendsite = new Friendsite();
		BeanUtil.populate(friendsite, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.friendsiteService.searchFriendsite(page, friendsite);
		
		
		
		request.setAttribute("page", page);
		request.setAttribute("bean", friendsite);

		return "/admin/friendsite/listFriendsite.jsp";
	}
	
	/**
	 * 导出友情链接数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportFriendsiteList")
	public String exportFriendsiteList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {
			
		// 查询需要友情链接的友情链接列表
		Friendsite friendsite = new Friendsite();
		BeanUtil.populate(friendsite, request.getParameterMap());
		List<Friendsite> excelContent = this.friendsiteService.searchFriendsiteForList(friendsite);

		String[] columnNames = new String[] { "Name" ,"Type" ,"Link" ,"Description" ,"CreateTime" ,"PicPath" ,"OrderNumber" ,"CreateUserId" ,"CreateUserName" };

		String titleName = "friendsiteList.xls";// 不创建说明;
		String sheetName = "友情链接列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.friendsiteService.exportFriendsiteList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}
	

}
