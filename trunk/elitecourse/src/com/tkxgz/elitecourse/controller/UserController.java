package com.tkxgz.elitecourse.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxgz.elitecourse.bean.Classes;
import com.tkxgz.elitecourse.bean.Group;
import com.tkxgz.elitecourse.bean.Log;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.core.constant.CommonConstants;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.ClassesService;
import com.tkxgz.elitecourse.service.DataService;
import com.tkxgz.elitecourse.service.DictService;
import com.tkxgz.elitecourse.service.GroupService;
import com.tkxgz.elitecourse.service.LogService;
import com.tkxgz.elitecourse.service.UserService;

/**
 * 用户Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/user.do")
public class UserController {

	private static final String MODULE_NAME = "用户管理";

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LogService logService;

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private ClassesService classesService;

	@Autowired
	private DictService dictService;

	@Autowired
	private DataService dataService;

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

		return this.listUser(request, currentPageNum);
	}

	/**
	 * 查询用户列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listUser")
	public String listUser(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.userService.listUser(page);

		request.setAttribute("page", page);

		List<Group> groupList = this.groupService.listAllGroup();
		request.setAttribute("groupList", groupList);
		List<Classes> classesList = this.classesService.listAllClasses();
		request.setAttribute("classesList", classesList);

		return "/admin/user/listUser.jsp";
	}

	/**
	 * 转到新增用户页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddUser")
	public String toAddUser(HttpServletRequest request) {

		List<Group> groupList = this.groupService.listAllGroup();
		request.setAttribute("groupList", groupList);
		List<Classes> classesList = this.classesService.listAllClasses();
		request.setAttribute("classesList", classesList);

		return "/admin/user/addUser.jsp";
	}

	/**
	 * 新增用户
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addUser")
	public String addUser(HttpServletRequest request) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/user.do?action=listUser";

		// 获取表单参数
		User user = new User();
		BeanUtil.populate(user, request.getParameterMap());

		// 设置操作管理员
		user.setCreateUserId(admin.getId());
		user.setCreateUserName(admin.getName());

		int affectedRows = this.userService.addUser(user);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddUser(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加用户");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 删除用户
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteUser")
	public String deleteUser(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.userService.batchDeleteUser(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除用户");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改用户页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateUser")
	public String toUpdateUser(HttpServletRequest request, String id) {

		Map map = this.userService.getUserById(id);

		request.setAttribute("map", map);

		List<Group> groupList = this.groupService.listAllGroup();
		request.setAttribute("groupList", groupList);
		List<Classes> classesList = this.classesService.listAllClasses();
		request.setAttribute("classesList", classesList);

		return "/admin/user/updateUser.jsp";
	}

	/**
	 * 修改用户
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
	@RequestMapping(params = "action=updateUser")
	public String updateUser(HttpServletRequest request, String id)
			throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/user.do?action=listUser";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		User user = this.userService.findUserById(id);
		BeanUtil.populate(user, request.getParameterMap());

		// 设置更新操作管理员
		user.setUpdateUserId(admin.getId());
		user.setUpdateUserName(admin.getName());

		int affectedRows = this.userService.updateUser(user);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddUser(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改用户");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看用户
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewUser")
	public String viewUser(HttpServletRequest request, String id) {

		Map map = this.userService.getUserById(id);

		request.setAttribute("map", map);

		return "/admin/user/viewUser.jsp";
	}

	/**
	 * 分页搜索用户列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchUser")
	public String searchUser(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		User user = new User();
		BeanUtil.populate(user, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.userService.searchUser(page, user);

		List<Group> groupList = this.groupService.listAllGroup();
		request.setAttribute("groupList", groupList);
		List<Classes> classesList = this.classesService.listAllClasses();
		request.setAttribute("classesList", classesList);

		request.setAttribute("page", page);
		request.setAttribute("bean", user);

		return "/admin/user/listUser.jsp";
	}

	/**
	 * 导出用户数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportUserList")
	public String exportUserList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {

		// 查询需要用户的用户列表
		User user = new User();
		BeanUtil.populate(user, request.getParameterMap());
		List<User> excelContent = this.userService.searchUserForList(user);

		String[] columnNames = new String[] { "用户名", "真实姓名", "性别", "出生日期",
				"组ID", "邮箱", "电话", "身份", "年龄", "角色", "班级", "创建用户名", "创建用户ID",
				"创建时间", "更新用户名", "更新用户ID", "更新时间", "备注" };

		String titleName = "userList.xls";// 不创建说明;
		String sheetName = "用户列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.userService.exportUserList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}

	@RequestMapping(params = "action=toImportStudent")
	public String toImportStudent(HttpServletRequest request) {

		return "/admin/user/importUser.jsp";
	}

	@RequestMapping(params = "action=importStudent")
	public String importStudent(HttpServletRequest request) {
		// 检查是否multipart的enctype
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		int result = 0;
		if (isMultipart) {
			ServletFileUpload upload = new ServletFileUpload();

			List<User> duplicateStudentData = new ArrayList<User>();// 重复学生数据，新增不成功

			FileItemIterator iter;
			try {
				iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
					FileItemStream item = iter.next();
					InputStream is = item.openStream();
					// 普通的表单域
					if (item.isFormField()) {
						// todo
					} else {// 文件上传域
						// 如果直接取item.getName得到文件名，在IE中上传的话，会得出本地全路径"C:\test.xls"，
						// 而其他浏览器得出"test.xls",但我们要的是"test"部分，因此，需要去掉前面的本地盘符信息
						String fileBaseName = FilenameUtils.getBaseName(item
								.getName());
						String fileExtension = FilenameUtils.getExtension(item
								.getName());
						// 保存到服务器的文件名后面加上"-",再加10位由字母及数字组成的随机数
						String fileName = fileBaseName + "_"
								+ RandomStringUtils.randomAlphanumeric(10)
								+ "." + fileExtension;

						String uploadFilePath = this.dictService
								.getDictByCode("uploadFilePath").getDictValue() + "/import/";

						File file = new File(uploadFilePath);

						// 上传路径不存在则创建,存在则直接返回，忽略
						file.mkdirs();

						final String filePath = uploadFilePath + fileName;

						if (is.available() != 0) {
							Streams.copy(is, new FileOutputStream(filePath),
									true);
							List<Map<Integer, String>> studentList = dataService
									.extractExcelData(filePath, fileExtension);

							result = this.dataService.batchAddStudent(
									studentList, duplicateStudentData);
							request.setAttribute("duplicateStudentData",
									duplicateStudentData);
						}
						// 导入完成后，删除服务器上的临时文件
						new File(filePath).delete();

					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String message = "成功导入" + result + "条记录";
		request.setAttribute("successImportCount", result);
		// return "redirect:/student.do?action=listStudent";
		return "/admin/user/importResult.jsp";
	}

}
