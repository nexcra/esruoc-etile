package com.tkxgz.elitecourse.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxgz.elitecourse.bean.Log;
import com.tkxgz.elitecourse.bean.Node;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.core.constant.CommonConstants;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.LogService;
import com.tkxgz.elitecourse.service.NodeService;
import com.tkxgz.elitecourse.service.UserService;

/**
 * 节点Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/node.do")
public class NodeController {

	private static final String MODULE_NAME = "节点管理";

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LogService logService;

	@Autowired
	private NodeService nodeService;

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

		return this.listNode(request);
	}

	/**
	 * 查询节点列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listNode")
	public String listNode(HttpServletRequest request) {

		List<Node> nodeList = this.nodeService.listNodeTreeByParentId("0");

		request.setAttribute("nodeList", nodeList);

		return "/admin/node/listNode.jsp";
	}

	/**
	 * 转到新增节点页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddNode")
	public String toAddNode(HttpServletRequest request) {

		List<Node> nodeList = this.nodeService.listNodeTreeByParentId("0");

		request.setAttribute("nodeList", nodeList);

		return "/admin/node/addNode.jsp";
	}

	/**
	 * 新增节点
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addNode")
	public String addNode(HttpServletRequest request, String parentParams)
			throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/node.do?action=listNode";

		// 获取表单参数
		Node node = new Node();
		BeanUtil.populate(node, request.getParameterMap());

		String parentFullPathId = parentParams.split("@@")[0];

		String parentFullPathName = parentParams.split("@@")[1];

		String parentLevel = parentParams.split("@@")[2];

		String parentId = parentParams.split("@@")[3];

		int nodeId = this.nodeService.getCurrentMaxId() + 1;// 加1作为主键

		node.setId(String.valueOf(nodeId));
		if ("0".equalsIgnoreCase(parentLevel)) {
			node.setFullPathName(node.getName());
			node.setFullPathId(String.valueOf(nodeId));
		} else {
			node.setFullPathId(parentFullPathId + "-" + nodeId);
			node.setFullPathName(parentFullPathName + "-" + node.getName());
		}

		node.setLevel(String.valueOf((Integer.valueOf(parentLevel) + 1)));
		node.setParentId(parentId);

		// 设置操作管理员
		node.setCreateUserId(admin.getId());
		node.setCreateUserName(admin.getName());

		int affectedRows = this.nodeService.addNode(node);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddNode(request);
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
	 * 删除节点
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteNode")
	public String deleteNode(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.nodeService.batchDeleteNode(ids);

		response.getWriter().write(" 成功删除 " + result + "条父记录，子记录也已级联删除");

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除节点");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改节点页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateNode")
	public String toUpdateNode(HttpServletRequest request, String id) {

		Map map = this.nodeService.getNodeById(id);

		request.setAttribute("map", map);

		List<Node> nodeList = this.nodeService.listNodeTreeByParentId("0");

		request.setAttribute("nodeList", nodeList);

		return "/admin/node/updateNode.jsp";
	}

	/**
	 * 修改节点
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
	@RequestMapping(params = "action=updateNode")
	public String updateNode(HttpServletRequest request, String id,
			String originParentId, String originFullPathId,
			String originFullPathName, String parentParams) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/node.do?action=listNode";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		Node node = this.nodeService.findNodeById(id);
		BeanUtil.populate(node, request.getParameterMap());
		String parentFullPathId = parentParams.split("@@")[0];

		String parentFullPathName = parentParams.split("@@")[1];

		String parentLevel = parentParams.split("@@")[2];

		String parentId = parentParams.split("@@")[3];

		if ("0".equalsIgnoreCase(parentLevel)) {
			node.setFullPathId(parentFullPathId + "_" + node.getId());
			node.setFullPathName(node.getName());
		} else {
			node.setFullPathId(parentFullPathId + "-" + node.getId());
			node.setFullPathName(parentFullPathName + "-" + node.getName());
		}

		node.setLevel(String.valueOf((Integer.valueOf(parentLevel) + 1)));
		node.setParentId(parentId);

		int affectedRows = this.nodeService.updateNode(node);// 修改

		// 如果本节点的层级关系有改变，则修改与之相关的子节点，主要是因为本节点的层级改变了，其子节点的父节点也应该改变
		if (!originParentId.equalsIgnoreCase(node.getParentId())) {
			// 找出需要修改节点路径的节点
			List<Node> needToChangeNodeList = this.nodeService
					.listNodesByFullPathId(originFullPathId);
			for (Node node2 : needToChangeNodeList) {
				String node2FullPathId = node2.getFullPathId();
				String node2FullPathName = node2.getFullPathName();
				node2FullPathId = node2FullPathId.replace(originFullPathId,
						node.getFullPathId());
				node2FullPathName = node2FullPathName.replace(
						originFullPathName, node.getFullPathName());
				// node2.setParentId(node.getId());
				node2.setFullPathId(node2FullPathId);
				node2.setFullPathName(node2FullPathName);
				node2.setLevel(String.valueOf(node2FullPathId.split("-").length));
				this.nodeService.updateNode(node2);
			}

		}

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddNode(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改节点");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看节点
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewNode")
	public String viewNode(HttpServletRequest request, String id) {

		Map map = this.nodeService.getNodeById(id);

		request.setAttribute("map", map);

		return "/admin/node/viewNode.jsp";
	}

	/**
	 * 分页搜索节点列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchNode")
	public String searchNode(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		Node node = new Node();
		BeanUtil.populate(node, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.nodeService.searchNode(page, node);

		request.setAttribute("page", page);
		request.setAttribute("bean", node);

		return "/admin/node/listNode.jsp";
	}

	/**
	 * 导出节点数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportNodeList")
	public String exportNodeList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {

		// 查询需要节点的节点列表
		Node node = new Node();
		BeanUtil.populate(node, request.getParameterMap());
		List<Node> excelContent = this.nodeService.searchNodeForList(node);

		String[] columnNames = new String[] { "Name", "ParentId", "Content",
				"IsLeaf", "FullPathName", "OrderNumber", "Type", "FullPathId",
				"Link", "CreateTime", "Level", "CreateUserId", "CreateUserName" };

		String titleName = "nodeList.xls";// 不创建说明;
		String sheetName = "节点列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.nodeService.exportNodeList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}

}
