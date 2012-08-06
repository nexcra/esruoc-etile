package com.tkxwz.esruocetile.webapp.service;

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

import com.tkxwz.esruocetile.webapp.dao.DataDao;
import com.tkxwz.esruocetile.webapp.dao.StudentDao;
import com.tkxwz.esruocetile.webapp.entity.Student;

/**
 * @author Po Kong
 * @since 2012-7-5 下午2:10:09
 */

@Service
public class DataService {

	@Autowired
	private DataDao dataDao;

	@Autowired
	private StudentDao studentDao;

	/**
	 * 判断是否为excel2003之前的版本
	 * 
	 * @author Po Kong
	 * @since 2012-7-6 下午4:30:32
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
	 * @author Po Kong
	 * @since 2012-7-5 下午2:12:05
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
	 * @author Po Kong
	 * @since 2012-7-6 下午4:37:21
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

	public int batchAddStudent(List<Map<Integer, String>> studentList) {
		Student student = new Student();
		int result = 0;
		for (int i = 0; i < studentList.size(); i++) {
			Map<Integer, String> map = studentList.get(i);
			student.setCollege(map.get(0));
			student.setGrade(map.get(1));
			student.setStudentNo(map.get(2));
			student.setName(map.get(3));
			student.setGender(map.get(4));

			student.setNotionalityCode(map.get(5));
			student.setNationality(map.get(6));
			student.setDateOfBirth(map.get(7));
			student.setIdNo(map.get(8));
			student.setMajor(map.get(9));
			student.setExecutiveClaas(map.get(10));

			result += this.studentDao.addStudent(student);
		}
		return result;
	}
}
