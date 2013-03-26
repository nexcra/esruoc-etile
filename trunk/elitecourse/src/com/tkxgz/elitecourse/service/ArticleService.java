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
import com.tkxgz.elitecourse.dao.ArticleDao;
import com.tkxgz.elitecourse.bean.Article;

/**
 * 文章Service类
 * 
 * @author Soyi Yao
 */
@Service
public class ArticleService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ArticleDao articleDao;

	/**
	 * 分页查询文章列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 文章分页数据
	 */
	public Page listArticle(Page page) {

		return this.articleDao.listArticle(page);
	}

	/**
	 * 添加文章
	 * 
	 * @author Soyi Yao
	 * @param article
	 *        文章bean
	 * @return
	 */
	public int addArticle(Article article) {

		return this.articleDao.addArticle(article);
	}

	/**
	 * 删除文章
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的文章id
	 * @return 影响行数
	 */
	public int deleteArticleById(String id) {

		return this.articleDao.deleteArticleById(id);
	}

	/**
	 * 批量删除文章
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        文章id数组
	 * @return 影响行数
	 */
	public int batchDeleteArticle(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteArticleById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据文章id查询文章详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        文章id
	 * @return 文章Map
	 */
	public Map getArticleById(String id) {

		return this.articleDao.getArticleById(id);
	}

	/**
	 * 根据文章id查询文章实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public Article findArticleById(String id) {

		return this.articleDao.findArticleById(id);
	}

	/**
	 * 更新文章
	 * 
	 * @author Soyi Yao
	 * @param article
	 *        文章bean
	 * @return 影响行数
	 */
	public int updateArticle(Article article) {

		return this.articleDao.updateArticle(article);
	}

	/**
	 * 分页搜索文章列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param article
	 *        文章bean
	 * @return 分页文章列表
	 */
	public Page searchArticle(Page page, Article article) {

		return this.articleDao.searchArticle(page, article);
	}

	/**
	 * 列出所有文章列表
	 * 
	 * @author Soyi Yao
	 * @return 文章列表
	 */
	public List<Article> listAllArticle() {
		return this.articleDao.listAllArticle();

	}

	/**
	 * 搜索文章列表
	 * 
	 * @author Soyi Yao
	 * @param article
	 *        Article实体
	 * @return 文章列表
	 */
	public List<Article> searchArticleForList(Article article) {

		return this.articleDao.searchArticleForList(article);
	}

	/**
	 * excel导出文章列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的文章内容列表
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
	public void exportArticleList(HttpServletRequest request,
			List<Article> excelContent, String[] columnNames, String sheetName,
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
			cellValue = (String) excelContent.get(i).getTitle();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getName();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getSubTitle();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getType();
			if ("1".equalsIgnoreCase(cellValue)) {
				cellValue = "本站原创";
			} else {
				cellValue = "网络转载";
			}
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getContent();
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

		}

		request.setCharacterEncoding(CommonConstants.CHARACTER_GBK);

		MessageFormat.format("{0}/{1}.xls", request.getRealPath("exportExcel"),
				Long.toString(System.currentTimeMillis()));

		wb.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}

	/**
	 * 获取t_article表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_article表记录总数
	 */
	public int getTotalRecords() {
		return this.articleDao.getTotalRecords();
	}

	/**
	 * @author Soyi Yao
	 * @param id
	 * @return
	 */
	public Page listArticleByColumnId(String columnId, Page page) {
		return this.articleDao.listArticleByColumnId(columnId, page);
	}
}
