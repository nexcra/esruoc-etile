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
import com.tkxgz.elitecourse.dao.ExamClassDao;
import com.tkxgz.elitecourse.bean.ExamClass;

/**
 * 考试分类Service类
 * 
 * @author Soyi Yao
 */
@Service
public class ExamClassService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExamClassDao examClassDao;

	/**
	 * 分页查询考试分类列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 考试分类分页数据
	 */
	public Page listExamClass(Page page) {

		return this.examClassDao.listExamClass(page);
	}

	/**
	 * 添加考试分类
	 * 
	 * @author Soyi Yao
	 * @param examClass
	 *        考试分类bean
	 * @return
	 */
	public int addExamClass(ExamClass examClass) {

		return this.examClassDao.addExamClass(examClass);
	}

	/**
	 * 删除考试分类
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的考试分类id
	 * @return 影响行数
	 */
	public int deleteExamClassById(String id) {

		return this.examClassDao.deleteExamClassById(id);
	}

	/**
	 * 批量删除考试分类
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        考试分类id数组
	 * @return 影响行数
	 */
	public int batchDeleteExamClass(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteExamClassById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据考试分类id查询考试分类详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        考试分类id
	 * @return 考试分类Map
	 */
	public Map getExamClassById(String id) {

		return this.examClassDao.getExamClassById(id);
	}

	/**
	 * 根据考试分类id查询考试分类实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public ExamClass findExamClassById(String id) {

		return this.examClassDao.findExamClassById(id);
	}

	/**
	 * 更新考试分类
	 * 
	 * @author Soyi Yao
	 * @param examClass
	 *        考试分类bean
	 * @return 影响行数
	 */
	public int updateExamClass(ExamClass examClass) {

		return this.examClassDao.updateExamClass(examClass);
	}


	/**
	 * 分页搜索考试分类列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param examClass
	 *        考试分类bean
	 * @return 分页考试分类列表
	 */
	public Page searchExamClass(Page page, ExamClass examClass) {

		return this.examClassDao.searchExamClass(page, examClass);
	}
	
	/**
	 * 列出所有考试分类列表
	 * 
	 * @author Soyi Yao
	 * @return 考试分类列表
	 */
	public List<ExamClass> listAllExamClass() {
		return this.examClassDao.listAllExamClass();

	}
	
	/**
	 * 搜索考试分类列表
	 * 
	 * @author Soyi Yao
	 * @param examClass
	 *        ExamClass实体
	 * @return 考试分类列表
	 */
	public List<ExamClass> searchExamClassForList(ExamClass examClass) {

		return this.examClassDao.searchExamClassForList(examClass);
	}
	
	/**
	 * excel导出考试分类列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的考试分类内容列表
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
	public void exportExamClassList(HttpServletRequest request,
			List<ExamClass> excelContent, String[] columnNames, String sheetName,
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
                         cellValue  =  (String)  excelContent.get(i).getName();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getOrderNumber();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getTotalMark();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getRadioSubjectNum();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getStatus();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getRadioSubjectMark();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCheckboxSubjectNumber();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCheckboxSubjectMark();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getJudgeSubjectNumber();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getJudgeSubjectMark();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getBlankSubjectNumber();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getBlankSubjectMark();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCreateTime();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCreateUserId();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCreateUserName();
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
	 * 获取t_exam_class表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_exam_class表记录总数
	 */
	public int getTotalRecords() {
		return this.examClassDao.getTotalRecords();
	}
}
