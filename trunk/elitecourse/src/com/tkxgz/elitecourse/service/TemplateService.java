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

import com.tkxgz.elitecourse.codegenerator.CodeGeneratorConstants;
import com.tkxgz.elitecourse.codegenerator.CodeGeneratorUtils;
import com.tkxgz.elitecourse.core.constant.CommonConstants;

import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.dao.TemplateDao;
import com.tkxgz.elitecourse.bean.Template;

/**
 * 模板Service类
 * 
 * @author Soyi Yao
 */
@Service
public class TemplateService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TemplateDao templateDao;

	/**
	 * 分页查询模板列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 模板分页数据
	 */
	public Page listTemplate(Page page) {

		return this.templateDao.listTemplate(page);
	}

	/**
	 * 添加模板
	 * 
	 * @author Soyi Yao
	 * @param template
	 *        模板bean
	 * @return
	 */
	public int addTemplate(Template template) {

		return this.templateDao.addTemplate(template);
	}

	/**
	 * 删除模板
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的模板id
	 * @return 影响行数
	 */
	public int deleteTemplateById(String id) {

		return this.templateDao.deleteTemplateById(id);
	}

	/**
	 * 批量删除模板
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        模板id数组
	 * @return 影响行数
	 */
	public int batchDeleteTemplate(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteTemplateById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据模板id查询模板详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        模板id
	 * @return 模板Map
	 */
	public Map getTemplateById(String id) {

		return this.templateDao.getTemplateById(id);
	}

	/**
	 * 根据模板id查询模板实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public Template findTemplateById(String id) {

		return this.templateDao.findTemplateById(id);
	}

	/**
	 * 更新模板
	 * 
	 * @author Soyi Yao
	 * @param template
	 *        模板bean
	 * @return 影响行数
	 */
	public int updateTemplate(Template template) {

		return this.templateDao.updateTemplate(template);
	}

	/**
	 * 分页搜索模板列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param template
	 *        模板bean
	 * @return 分页模板列表
	 */
	public Page searchTemplate(Page page, Template template) {

		return this.templateDao.searchTemplate(page, template);
	}

	/**
	 * 列出所有模板列表
	 * 
	 * @author Soyi Yao
	 * @return 模板列表
	 */
	public List<Template> listAllTemplate() {
		return this.templateDao.listAllTemplate();

	}

	/**
	 * 搜索模板列表
	 * 
	 * @author Soyi Yao
	 * @param template
	 *        Template实体
	 * @return 模板列表
	 */
	public List<Template> searchTemplateForList(Template template) {

		return this.templateDao.searchTemplateForList(template);
	}

	/**
	 * excel导出模板列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的模板内容列表
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
	public void exportTemplateList(HttpServletRequest request,
			List<Template> excelContent, String[] columnNames,
			String sheetName, OutputStream outputStream) throws IOException {

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
			cellValue = (String) excelContent.get(i).getTemplateName();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getDescription();
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
			cellValue = (String) excelContent.get(i).getFileName();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getTemplateCode();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getTemplateContent();
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
	 * 获取t_template表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_template表记录总数
	 */
	public int getTotalRecords() {
		return this.templateDao.getTotalRecords();
	}

	/**
	 * 获取t_template表当前最大id
	 */
	public int getCurrentMaxId() {
		return this.templateDao.getCurrentMaxId();
	}

	/**
	 * 写文件源码
	 * 
	 * @author Soyi Yao
	 * @param filePath
	 * @param source
	 * @return
	 */
	public boolean writeSource(String filePath, String content) {
		boolean result = true;
//		String content = CodeGeneratorUtils
//				.readSource(CodeGeneratorConstants.TEMPLATE_ROOT_FOLDER
//						+ CodeGeneratorConstants.MAPPER_FILE);
		
		CodeGeneratorUtils.createFile(filePath, content, true);
		return result;
	}

}
