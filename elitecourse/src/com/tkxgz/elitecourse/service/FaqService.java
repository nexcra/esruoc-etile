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
import com.tkxgz.elitecourse.dao.FaqDao;
import com.tkxgz.elitecourse.bean.Faq;

/**
 * 常见问题Service类
 * 
 * @author Soyi Yao
 */
@Service
public class FaqService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FaqDao faqDao;

	/**
	 * 分页查询常见问题列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 常见问题分页数据
	 */
	public Page listFaq(Page page) {

		return this.faqDao.listFaq(page);
	}

	/**
	 * 添加常见问题
	 * 
	 * @author Soyi Yao
	 * @param faq
	 *        常见问题bean
	 * @return
	 */
	public int addFaq(Faq faq) {

		return this.faqDao.addFaq(faq);
	}

	/**
	 * 删除常见问题
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的常见问题id
	 * @return 影响行数
	 */
	public int deleteFaqById(String id) {

		return this.faqDao.deleteFaqById(id);
	}

	/**
	 * 批量删除常见问题
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        常见问题id数组
	 * @return 影响行数
	 */
	public int batchDeleteFaq(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteFaqById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据常见问题id查询常见问题详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        常见问题id
	 * @return 常见问题Map
	 */
	public Map getFaqById(String id) {

		return this.faqDao.getFaqById(id);
	}

	/**
	 * 根据常见问题id查询常见问题实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public Faq findFaqById(String id) {

		return this.faqDao.findFaqById(id);
	}

	/**
	 * 更新常见问题
	 * 
	 * @author Soyi Yao
	 * @param faq
	 *        常见问题bean
	 * @return 影响行数
	 */
	public int updateFaq(Faq faq) {

		return this.faqDao.updateFaq(faq);
	}


	/**
	 * 分页搜索常见问题列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param faq
	 *        常见问题bean
	 * @return 分页常见问题列表
	 */
	public Page searchFaq(Page page, Faq faq) {

		return this.faqDao.searchFaq(page, faq);
	}
	
	/**
	 * 列出所有常见问题列表
	 * 
	 * @author Soyi Yao
	 * @return 常见问题列表
	 */
	public List<Faq> listAllFaq() {
		return this.faqDao.listAllFaq();

	}
	
	/**
	 * 搜索常见问题列表
	 * 
	 * @author Soyi Yao
	 * @param faq
	 *        Faq实体
	 * @return 常见问题列表
	 */
	public List<Faq> searchFaqForList(Faq faq) {

		return this.faqDao.searchFaqForList(faq);
	}
	
	/**
	 * excel导出常见问题列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的常见问题内容列表
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
	public void exportFaqList(HttpServletRequest request,
			List<Faq> excelContent, String[] columnNames, String sheetName,
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
                         cellValue  =  (String)  excelContent.get(i).getQuestion();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getAnswer();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getStatus();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getReference();
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

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCreateTime();
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
	 * 获取t_faq表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_faq表记录总数
	 */
	public int getTotalRecords() {
		return this.faqDao.getTotalRecords();
	}
}
