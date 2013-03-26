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
import com.tkxgz.elitecourse.dao.FriendsiteDao;
import com.tkxgz.elitecourse.bean.Friendsite;

/**
 * 友情链接Service类
 * 
 * @author Soyi Yao
 */
@Service
public class FriendsiteService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FriendsiteDao friendsiteDao;

	/**
	 * 分页查询友情链接列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 友情链接分页数据
	 */
	public Page listFriendsite(Page page) {

		return this.friendsiteDao.listFriendsite(page);
	}

	/**
	 * 添加友情链接
	 * 
	 * @author Soyi Yao
	 * @param friendsite
	 *        友情链接bean
	 * @return
	 */
	public int addFriendsite(Friendsite friendsite) {

		return this.friendsiteDao.addFriendsite(friendsite);
	}

	/**
	 * 删除友情链接
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的友情链接id
	 * @return 影响行数
	 */
	public int deleteFriendsiteById(String id) {

		return this.friendsiteDao.deleteFriendsiteById(id);
	}

	/**
	 * 批量删除友情链接
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        友情链接id数组
	 * @return 影响行数
	 */
	public int batchDeleteFriendsite(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteFriendsiteById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据友情链接id查询友情链接详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        友情链接id
	 * @return 友情链接Map
	 */
	public Map getFriendsiteById(String id) {

		return this.friendsiteDao.getFriendsiteById(id);
	}

	/**
	 * 根据友情链接id查询友情链接实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public Friendsite findFriendsiteById(String id) {

		return this.friendsiteDao.findFriendsiteById(id);
	}

	/**
	 * 更新友情链接
	 * 
	 * @author Soyi Yao
	 * @param friendsite
	 *        友情链接bean
	 * @return 影响行数
	 */
	public int updateFriendsite(Friendsite friendsite) {

		return this.friendsiteDao.updateFriendsite(friendsite);
	}


	/**
	 * 分页搜索友情链接列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param friendsite
	 *        友情链接bean
	 * @return 分页友情链接列表
	 */
	public Page searchFriendsite(Page page, Friendsite friendsite) {

		return this.friendsiteDao.searchFriendsite(page, friendsite);
	}
	
	/**
	 * 列出所有友情链接列表
	 * 
	 * @author Soyi Yao
	 * @return 友情链接列表
	 */
	public List<Friendsite> listAllFriendsite() {
		return this.friendsiteDao.listAllFriendsite();

	}
	
	/**
	 * 搜索友情链接列表
	 * 
	 * @author Soyi Yao
	 * @param friendsite
	 *        Friendsite实体
	 * @return 友情链接列表
	 */
	public List<Friendsite> searchFriendsiteForList(Friendsite friendsite) {

		return this.friendsiteDao.searchFriendsiteForList(friendsite);
	}
	
	/**
	 * excel导出友情链接列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的友情链接内容列表
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
	public void exportFriendsiteList(HttpServletRequest request,
			List<Friendsite> excelContent, String[] columnNames, String sheetName,
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
                         cellValue  =  (String)  excelContent.get(i).getType();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getLink();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getDescription();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCreateTime();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getPicPath();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getOrderNumber();
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
	 * 获取t_friendsite表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_friendsite表记录总数
	 */
	public int getTotalRecords() {
		return this.friendsiteDao.getTotalRecords();
	}
}
