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
import com.tkxgz.elitecourse.dao.CourseIntroductionDao;
import com.tkxgz.elitecourse.bean.CourseIntroduction;

/**
 * 课程简介Service类
 * 
 * @author Soyi Yao
 */
@Service
public class CourseIntroductionService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseIntroductionDao courseIntroductionDao;

	/**
	 * 分页查询课程简介列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 课程简介分页数据
	 */
	public Page listCourseIntroduction(Page page) {

		return this.courseIntroductionDao.listCourseIntroduction(page);
	}

	/**
	 * 添加课程简介
	 * 
	 * @author Soyi Yao
	 * @param courseIntroduction
	 *        课程简介bean
	 * @return
	 */
	public int addCourseIntroduction(CourseIntroduction courseIntroduction) {

		return this.courseIntroductionDao.addCourseIntroduction(courseIntroduction);
	}

	/**
	 * 删除课程简介
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的课程简介id
	 * @return 影响行数
	 */
	public int deleteCourseIntroductionById(String id) {

		return this.courseIntroductionDao.deleteCourseIntroductionById(id);
	}

	/**
	 * 批量删除课程简介
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        课程简介id数组
	 * @return 影响行数
	 */
	public int batchDeleteCourseIntroduction(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteCourseIntroductionById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据课程简介id查询课程简介详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        课程简介id
	 * @return 课程简介Map
	 */
	public Map getCourseIntroductionById(String id) {

		return this.courseIntroductionDao.getCourseIntroductionById(id);
	}

	/**
	 * 根据课程简介id查询课程简介实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public CourseIntroduction findCourseIntroductionById(String id) {

		return this.courseIntroductionDao.findCourseIntroductionById(id);
	}

	/**
	 * 更新课程简介
	 * 
	 * @author Soyi Yao
	 * @param courseIntroduction
	 *        课程简介bean
	 * @return 影响行数
	 */
	public int updateCourseIntroduction(CourseIntroduction courseIntroduction) {

		return this.courseIntroductionDao.updateCourseIntroduction(courseIntroduction);
	}


	/**
	 * 分页搜索课程简介列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param courseIntroduction
	 *        课程简介bean
	 * @return 分页课程简介列表
	 */
	public Page searchCourseIntroduction(Page page, CourseIntroduction courseIntroduction) {

		return this.courseIntroductionDao.searchCourseIntroduction(page, courseIntroduction);
	}
	
	/**
	 * 列出所有课程简介列表
	 * 
	 * @author Soyi Yao
	 * @return 课程简介列表
	 */
	public List<CourseIntroduction> listAllCourseIntroduction() {
		return this.courseIntroductionDao.listAllCourseIntroduction();

	}
	
	/**
	 * 搜索课程简介列表
	 * 
	 * @author Soyi Yao
	 * @param courseIntroduction
	 *        CourseIntroduction实体
	 * @return 课程简介列表
	 */
	public List<CourseIntroduction> searchCourseIntroductionForList(CourseIntroduction courseIntroduction) {

		return this.courseIntroductionDao.searchCourseIntroductionForList(courseIntroduction);
	}
	
	/**
	 * excel导出课程简介列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的课程简介内容列表
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
	public void exportCourseIntroductionList(HttpServletRequest request,
			List<CourseIntroduction> excelContent, String[] columnNames, String sheetName,
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
                         cellValue  =  (String)  excelContent.get(i).getCourseHostName();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getIntroContent();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getPic();
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
	 * 获取t_course_introduction表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_course_introduction表记录总数
	 */
	public int getTotalRecords() {
		return this.courseIntroductionDao.getTotalRecords();
	}
}
