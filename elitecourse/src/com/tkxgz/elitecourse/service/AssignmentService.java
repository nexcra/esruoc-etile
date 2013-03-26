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
import com.tkxgz.elitecourse.dao.AssignmentDao;
import com.tkxgz.elitecourse.bean.Assignment;

/**
 * 作业Service类
 * 
 * @author Soyi Yao
 */
@Service
public class AssignmentService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AssignmentDao assignmentDao;

	/**
	 * 分页查询作业列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 作业分页数据
	 */
	public Page listAssignment(Page page) {

		return this.assignmentDao.listAssignment(page);
	}

	/**
	 * 添加作业
	 * 
	 * @author Soyi Yao
	 * @param assignment
	 *        作业bean
	 * @return
	 */
	public int addAssignment(Assignment assignment) {

		return this.assignmentDao.addAssignment(assignment);
	}

	/**
	 * 删除作业
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的作业id
	 * @return 影响行数
	 */
	public int deleteAssignmentById(String id) {

		return this.assignmentDao.deleteAssignmentById(id);
	}

	/**
	 * 批量删除作业
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        作业id数组
	 * @return 影响行数
	 */
	public int batchDeleteAssignment(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteAssignmentById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据作业id查询作业详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        作业id
	 * @return 作业Map
	 */
	public Map getAssignmentById(String id) {

		return this.assignmentDao.getAssignmentById(id);
	}

	/**
	 * 根据作业id查询作业实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public Assignment findAssignmentById(String id) {

		return this.assignmentDao.findAssignmentById(id);
	}

	/**
	 * 更新作业
	 * 
	 * @author Soyi Yao
	 * @param assignment
	 *        作业bean
	 * @return 影响行数
	 */
	public int updateAssignment(Assignment assignment) {

		return this.assignmentDao.updateAssignment(assignment);
	}


	/**
	 * 分页搜索作业列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param assignment
	 *        作业bean
	 * @return 分页作业列表
	 */
	public Page searchAssignment(Page page, Assignment assignment) {

		return this.assignmentDao.searchAssignment(page, assignment);
	}
	
	/**
	 * 列出所有作业列表
	 * 
	 * @author Soyi Yao
	 * @return 作业列表
	 */
	public List<Assignment> listAllAssignment() {
		return this.assignmentDao.listAllAssignment();

	}
	
	/**
	 * 搜索作业列表
	 * 
	 * @author Soyi Yao
	 * @param assignment
	 *        Assignment实体
	 * @return 作业列表
	 */
	public List<Assignment> searchAssignmentForList(Assignment assignment) {

		return this.assignmentDao.searchAssignmentForList(assignment);
	}
	
	/**
	 * excel导出作业列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的作业内容列表
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
	public void exportAssignmentList(HttpServletRequest request,
			List<Assignment> excelContent, String[] columnNames, String sheetName,
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

			//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getUserId();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getUserName();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getAssignmentClassId();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getSubmitTime();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getClassesId();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getDescription();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getContent();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getPath();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getGrade();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getReContent();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getReTime();
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
	 * 获取t_assignment表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_assignment表记录总数
	 */
	public int getTotalRecords() {
		return this.assignmentDao.getTotalRecords();
	}
}
