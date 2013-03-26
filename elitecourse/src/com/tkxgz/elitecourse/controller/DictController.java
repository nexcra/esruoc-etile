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

import com.tkxgz.elitecourse.bean.Dict;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.DictService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;



/**
 * 字典Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/dict.do")
public class DictController {

	private static final String MODULE_NAME = "字典管理";
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private DictService dictService;
	
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

		return this.listDict(request, currentPageNum);
	}

	/**
	 * 查询字典列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listDict")
	public String listDict(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.dictService.listDict(page);

		request.setAttribute("page", page);
		
		

		return "/admin/dict/listDict.jsp";
	}

	/**
	 * 转到新增字典页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddDict")
	public String toAddDict(HttpServletRequest request) {
		
		
		
		return "/admin/dict/addDict.jsp";
	}

	/**
	 * 新增字典
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addDict")
	public String addDict(HttpServletRequest request) throws Exception {
		
		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/dict.do?action=listDict";

		// 获取表单参数
		Dict dict = new Dict();
		BeanUtil.populate(dict, request.getParameterMap());
		
		
		

		int affectedRows = this.dictService.addDict(dict);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddDict(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加字典");

		this.logService.addLog(log);
		
		return result;
	}

	/**
	 * 删除字典
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteDict")
	public String deleteDict(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.dictService.batchDeleteDict(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");
		
	
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除字典");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改字典页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateDict")
	public String toUpdateDict(HttpServletRequest request, String id) {

		Map map = this.dictService.getDictById(id);

		request.setAttribute("map", map);
		
		

		return "/admin/dict/updateDict.jsp";
	}

	/**
	 * 修改字典
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
	@RequestMapping(params = "action=updateDict")
	public String updateDict(HttpServletRequest request, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/dict.do?action=listDict";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		Dict dict = this.dictService.findDictById(id);
		BeanUtil.populate(dict, request.getParameterMap());

		
		
		int affectedRows = this.dictService.updateDict(dict);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddDict(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改字典");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看字典
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewDict")
	public String viewDict(HttpServletRequest request, String id) {

		Map map = this.dictService.getDictById(id);

		request.setAttribute("map", map);

		return "/admin/dict/viewDict.jsp";
	}

	/**
	 * 分页搜索字典列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchDict")
	public String searchDict(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		Dict dict = new Dict();
		BeanUtil.populate(dict, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.dictService.searchDict(page, dict);
		
		
		
		request.setAttribute("page", page);
		request.setAttribute("bean", dict);

		return "/admin/dict/listDict.jsp";
	}
	
	/**
	 * 导出字典数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportDictList")
	public String exportDictList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {
			
		// 查询需要字典的字典列表
		Dict dict = new Dict();
		BeanUtil.populate(dict, request.getParameterMap());
		List<Dict> excelContent = this.dictService.searchDictForList(dict);

		String[] columnNames = new String[] { "DictCode" ,"DictName" ,"DictDesc" ,"DictValue" ,"Status" ,"IsApplicationLevel" };

		String titleName = "dictList.xls";// 不创建说明;
		String sheetName = "字典列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.dictService.exportDictList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}
	

}
