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
import com.tkxgz.elitecourse.dao.NoticeDao;
import com.tkxgz.elitecourse.bean.Notice;

/**
 * 公告Service类
 * 
 * @author Soyi Yao
 */
@Service
public class NoticeService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NoticeDao noticeDao;

	/**
	 * 分页查询公告列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 公告分页数据
	 */
	public Page listNotice(Page page) {

		return this.noticeDao.listNotice(page);
	}

	/**
	 * 添加公告
	 * 
	 * @author Soyi Yao
	 * @param notice
	 *        公告bean
	 * @return
	 */
	public int addNotice(Notice notice) {

		return this.noticeDao.addNotice(notice);
	}

	/**
	 * 删除公告
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的公告id
	 * @return 影响行数
	 */
	public int deleteNoticeById(String id) {

		return this.noticeDao.deleteNoticeById(id);
	}

	/**
	 * 批量删除公告
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        公告id数组
	 * @return 影响行数
	 */
	public int batchDeleteNotice(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteNoticeById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据公告id查询公告详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告Map
	 */
	public Map getNoticeById(String id) {

		return this.noticeDao.getNoticeById(id);
	}

	/**
	 * 根据公告id查询公告实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public Notice findNoticeById(String id) {

		return this.noticeDao.findNoticeById(id);
	}

	/**
	 * 更新公告
	 * 
	 * @author Soyi Yao
	 * @param notice
	 *        公告bean
	 * @return 影响行数
	 */
	public int updateNotice(Notice notice) {

		return this.noticeDao.updateNotice(notice);
	}


	/**
	 * 分页搜索公告列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param notice
	 *        公告bean
	 * @return 分页公告列表
	 */
	public Page searchNotice(Page page, Notice notice) {

		return this.noticeDao.searchNotice(page, notice);
	}
	
	/**
	 * 列出所有公告列表
	 * 
	 * @author Soyi Yao
	 * @return 公告列表
	 */
	public List<Notice> listAllNotice() {
		return this.noticeDao.listAllNotice();

	}
	
	/**
	 * 搜索公告列表
	 * 
	 * @author Soyi Yao
	 * @param notice
	 *        Notice实体
	 * @return 公告列表
	 */
	public List<Notice> searchNoticeForList(Notice notice) {

		return this.noticeDao.searchNoticeForList(notice);
	}
	
	/**
	 * excel导出公告列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的公告内容列表
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
	public void exportNoticeList(HttpServletRequest request,
			List<Notice> excelContent, String[] columnNames, String sheetName,
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
                         cellValue  =  (String)  excelContent.get(i).getTitle();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getContent();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCreateTime();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCreateUserName();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCreateUserId();
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
	 * 获取t_notice表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_notice表记录总数
	 */
	public int getTotalRecords() {
		return this.noticeDao.getTotalRecords();
	}
}
