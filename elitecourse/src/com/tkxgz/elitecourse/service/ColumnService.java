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
import com.tkxgz.elitecourse.dao.ColumnDao;
import com.tkxgz.elitecourse.bean.Column;
import com.tkxgz.elitecourse.bean.Column;

/**
 * 栏目Service类
 * 
 * @author Soyi Yao
 */
@Service
public class ColumnService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ColumnDao columnDao;

	/**
	 * 分页查询栏目列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 栏目分页数据
	 */
	public Page listColumn(Page page) {

		return this.columnDao.listColumn(page);
	}

	/**
	 * 添加栏目
	 * 
	 * @author Soyi Yao
	 * @param column
	 *        栏目bean
	 * @return
	 */
	public int addColumn(Column column) {

		return this.columnDao.addColumn(column);
	}

	/**
	 * 删除栏目
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的栏目id
	 * @return 影响行数
	 */
	public int deleteColumnById(String id) {

		return this.columnDao.deleteColumnById(id);
	}

	/**
	 * 批量删除栏目
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        栏目id数组
	 * @return 影响行数
	 */
	public int batchDeleteColumn(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteColumnById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据栏目id查询栏目详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        栏目id
	 * @return 栏目Map
	 */
	public Map getColumnById(String id) {

		return this.columnDao.getColumnById(id);
	}

	/**
	 * 根据栏目id查询栏目实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public Column findColumnById(String id) {

		return this.columnDao.findColumnById(id);
	}

	/**
	 * 更新栏目
	 * 
	 * @author Soyi Yao
	 * @param column
	 *        栏目bean
	 * @return 影响行数
	 */
	public int updateColumn(Column column) {

		return this.columnDao.updateColumn(column);
	}

	/**
	 * 分页搜索栏目列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param column
	 *        栏目bean
	 * @return 分页栏目列表
	 */
	public Page searchColumn(Page page, Column column) {

		return this.columnDao.searchColumn(page, column);
	}

	/**
	 * 列出所有栏目列表
	 * 
	 * @author Soyi Yao
	 * @return 栏目列表
	 */
	public List<Column> listAllColumn() {
		return this.columnDao.listAllColumn();

	}
	/**
	 * 根据级数
	 * 
	 * @author Soyi Yao
	 * @return 栏目列表
	 */
	public List<Column> listColumnByLevel() {
		return this.columnDao.listAllColumn();
		
	}

	/**
	 * 搜索栏目列表
	 * 
	 * @author Soyi Yao
	 * @param column
	 *        Column实体
	 * @return 栏目列表
	 */
	public List<Column> searchColumnForList(Column column) {

		return this.columnDao.searchColumnForList(column);
	}

	/**
	 * excel导出栏目列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的栏目内容列表
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
	public void exportColumnList(HttpServletRequest request,
			List<Column> excelContent, String[] columnNames, String sheetName,
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
			cellValue = (String) excelContent.get(i).getParentId();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getName();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getCode();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getIsLeaf();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getLevel();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getFullPathId();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getFullPathName();
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

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getCreateTime();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getOrderNumber();
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
	 * 获取t_column表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_column表记录总数
	 */
	public int getTotalRecords() {
		return this.columnDao.getTotalRecords();
	}

	/**
	 * 获取t_column表当前最大id
	 */
	public int getCurrentMaxId() {
		return this.columnDao.getCurrentMaxId();
	}

	/**
	 * 栏目树列表
	 * 
	 * @author Soyi Yao
	 * @param parentId
	 *        父栏目id
	 * @return 栏目树数据
	 */
	public List<Column> listColumnTreeByParentId(String parentId) {
		return this.columnDao.listColumnTreeByParentId(parentId);
	}
}
