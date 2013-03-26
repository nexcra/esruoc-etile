package com.tkxgz.elitecourse.service;

import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tkxgz.elitecourse.core.constant.CommonConstants;

import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.dao.NodeDao;
import com.tkxgz.elitecourse.bean.Node;

/**
 * 节点Service类
 * 
 * @author Soyi Yao
 */
@Service
public class NodeService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NodeDao nodeDao;

	/**
	 * 分页查询节点列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 节点分页数据
	 */
	public Page listNode(Page page) {

		return this.nodeDao.listNode(page);
	}

	/**
	 * 添加节点
	 * 
	 * @author Soyi Yao
	 * @param node
	 *        节点bean
	 * @return
	 */
	public int addNode(Node node) {

		return this.nodeDao.addNode(node);
	}

	/**
	 * 删除节点
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的节点id
	 * @return 影响行数
	 */
	public int deleteNodeById(String id) {

		return this.nodeDao.deleteNodeById(id);
	}

	/**
	 * 批量删除节点
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        节点id数组
	 * @return 影响行数
	 */
	public int batchDeleteNode(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteNodeById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据节点id查询节点详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        节点id
	 * @return 节点Map
	 */
	public Map getNodeById(String id) {

		return this.nodeDao.getNodeById(id);
	}

	/**
	 * 根据节点id查询节点实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public Node findNodeById(String id) {

		return this.nodeDao.findNodeById(id);
	}

	/**
	 * 更新节点
	 * 
	 * @author Soyi Yao
	 * @param node
	 *        节点bean
	 * @return 影响行数
	 */
	public int updateNode(Node node) {

		return this.nodeDao.updateNode(node);
	}

	/**
	 * 分页搜索节点列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param node
	 *        节点bean
	 * @return 分页节点列表
	 */
	public Page searchNode(Page page, Node node) {

		return this.nodeDao.searchNode(page, node);
	}

	/**
	 * 列出所有节点列表
	 * 
	 * @author Soyi Yao
	 * @return 节点列表
	 */
	public List<Node> listAllNode() {
		return this.nodeDao.listAllNode();

	}

	/**
	 * 搜索节点列表
	 * 
	 * @author Soyi Yao
	 * @param node
	 *        Node实体
	 * @return 节点列表
	 */
	public List<Node> searchNodeForList(Node node) {

		return this.nodeDao.searchNodeForList(node);
	}

	/**
	 * excel导出节点列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的节点内容列表
	 * @param columnNames
	 *        导出excel标题数组
	 * @param sheetName
	 *        excel的Sheet名字
	 * @param outputStream
	 *        OutputStream
	 * @throws IOException
	 *         IO异常
	 */
	@SuppressWarnings("deprecation")
	public void exportNodeList(HttpServletRequest request,
			List<Node> excelContent, String[] columnNames, String sheetName,
			OutputStream outputStream) throws IOException {

		HSSFWorkbook wb = new HSSFWorkbook();

		// 标题列的字体样式
		HSSFFont headerFont = wb.createFont();
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setColor(HSSFFont.BOLDWEIGHT_NORMAL);

		// 标题列样式
		HSSFCellStyle headerStyle = wb.createCellStyle();
		headerStyle.setFont(headerFont);
		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		headerStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setHidden(true);

		// 内容列的样式
		HSSFCellStyle contentStyle = wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		contentStyle.setDataFormat(wb.createDataFormat().getFormat("0.00"));
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

		HSSFSheet sheet = wb.createSheet(sheetName);

		// 创建列名称
		HSSFRow headRow = sheet.createRow((short) 0);//
		headRow.setHeightInPoints(20.120f);

		// 生成标题列
		for (int i = 0; i < columnNames.length; i++) {
			HSSFCell cell = headRow.createCell((short) i);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(columnNames[i]);
		}

		// 生成内容列
		for (int i = 0; i < excelContent.size(); i++) {
			short cellNo = 0;

			// 建立新行
			HSSFRow row = sheet.createRow((short) i + 1);

			HSSFCell cell = null; // 单元格
			String cellValue = ""; // 单元格值

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getName();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getParentId();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getContent();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getIsLeaf();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getFullPathName();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getOrderNumber();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getType();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getFullPathId();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getLink();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getCreateTime();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getLevel();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getCreateUserId();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getCreateUserName();
			cell.setCellValue(cellValue);

		}

		request.setCharacterEncoding(CommonConstants.CHARACTER_GBK);

		MessageFormat.format("{0}/{1}.xls", request.getRealPath("exportExcel"),
				Long.toString(System.currentTimeMillis()));

		wb.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}

	/**
	 * 获取t_node表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_node表记录总数
	 */
	public int getTotalRecords() {
		return this.nodeDao.getTotalRecords();
	}

	/**
	 * 获取t_column表当前最大id
	 */
	public int getCurrentMaxId() {
		return this.nodeDao.getCurrentMaxId();
	}

	/**
	 * 节点树列表
	 * 
	 * @author Soyi Yao
	 * @param parentId
	 *        父节点id
	 * @return 节点树数据
	 */
	public List<Node> listNodeTreeByParentId(String parentId) {

		return this.nodeDao.listNodeTreeByParentId(parentId);
	}

	/**
	 * @author Soyi Yao
	 * @param parentId
	 */
	public List<Node> listChildNodesByParentId(String parentId) {
		return this.nodeDao.listChildNodesByParentId(parentId);

	}

	/**
	 * @author Soyi Yao
	 * @param originFullPathId
	 */
	public List<Node> listNodesByFullPathId(String originFullPathId) {
		return this.nodeDao.listNodesByFullPathId(originFullPathId);

	}
}
