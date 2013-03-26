package com.tkxgz.elitecourse.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.core.util.MD5Util;
import com.tkxgz.elitecourse.dao.UserDao;

/**
 * @author
 */

@Service
public class DataService {

	@Autowired
	private UserDao userDao;

	/**
	 * 判断是否为excel2003之前的版本
	 * 
	 * @author
	 * @param fileExtension
	 * @return
	 */
	public boolean isExcel2003(String fileExtension) {
		if ("xls".equals(fileExtension)) {
			return true;
		}
		return false;
	}

	/**
	 * 遍历excel中除第一行的每一行，读出每列的值，把列的值放入Map中，然后把Map放在List中存放并返回<br />
	 * 特别注意:在excel，空白字符串也是占一行的，因此这个时候需要排除出去
	 * 
	 * @author
	 * @param filePath
	 *        excel存放的路径
	 * @return
	 * @throws IOException
	 */
	public List<Map<Integer, String>> extractExcelData(String filePath,
			String fileExtension) throws IOException {
		InputStream inp = new FileInputStream(filePath);
		boolean isExcel2003 = this.isExcel2003(fileExtension);
		Workbook wb = getWorkbook(inp, isExcel2003);
		Sheet sheet = wb.getSheetAt(0);
		List<Map<Integer, String>> resultList = new ArrayList<Map<Integer, String>>();
		Map<Integer, String> map = null;
		boolean valid = true;// 一行数据是否有效，比如一些空行也是能读出来的，但肉眼可能看不出来，这些行应该直接忽略掉
		for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext();) {
			map = new HashMap<Integer, String>();
			Row row = rit.next();

			// 去掉excel表首行，一般用于说明列名
			if (row.getRowNum() == 0) {
				continue;
			}

			valid = true;

			for (Iterator<Cell> cit = row.cellIterator(); cit.hasNext();) {

				Cell cell = cit.next();
				String cellValue = "";
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					cellValue = cell.getStringCellValue();
				} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					cellValue = (int) (cell.getNumericCellValue()) + "";
				}
				// 如果发现一行中有一列为空，直接退出循环
				if (StringUtils.isEmpty(cellValue)) {
					valid = false;
					break;
				}
				map.put(cell.getColumnIndex(), cellValue);
			}
			if (valid) {
				resultList.add(map);
			}

		}

		return resultList;
	}

	/**
	 * @author
	 * @param inp
	 * @param isExcel2003
	 * @return
	 * @throws IOException
	 */
	private Workbook getWorkbook(InputStream inp, boolean isExcel2003)
			throws IOException {
		Workbook wb = null;
		if (isExcel2003) {
			wb = new HSSFWorkbook(new POIFSFileSystem(inp));
		} else {
			wb = new XSSFWorkbook(inp);
		}
		return wb;
	}

	public boolean exist(String name) {
		boolean result = false;
		int count = this.userDao.getUserCountByName(name);
		if (count > 0) {
			result = true;
		}
		return result;
	}

	public int batchAddStudent(List<Map<Integer, String>> studentList,
			List<User> duplicateStudentData) {
		int result = 0;
		for (int i = 0; i < studentList.size(); i++) {
			User user = new User();
			Map<Integer, String> map = studentList.get(i);
			user.setName(map.get(0));
			user.setRealName(map.get(1));

			user.setStudentNo(map.get(2));
			user.setPassword(MD5Util.MD5(map.get(3)));

			String gender = map.get(4);
			if ("男".equalsIgnoreCase(gender)) {
				user.setGender("male");
			} else {
				user.setGender("female");

			}
			user.setBirthDate(map.get(5));
			user.setEmail(map.get(6));
			user.setTelephone(map.get(7));
			user.setAge(map.get(8));
			user.setClassesId(map.get(9));

			user.setRemark(map.get(10));

			user.setIsAdmin("student");

			boolean exist = this.exist(user.getName());
			if (exist) {
				duplicateStudentData.add(user);
			} else {
				result += this.userDao.addUser(user);

			}
		}
		return result;
	}

}
