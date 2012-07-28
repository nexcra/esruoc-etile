package com.tkxwz.esruocetile.webapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.webapp.dao.StudentDao;
import com.tkxwz.esruocetile.webapp.entity.Student;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:14:39
 */
@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

	public Page listStudent(Page page) {
		return this.studentDao.listStudent(page);
	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 12:51:56
	 * @param student
	 */
	public int addStudent(Student student) {
		return this.studentDao.addStudent(student);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:12:54
	 * @param ids
	 */
	public int deleteStudentById(String id) {
		return this.studentDao.deleteStudentById(id);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:14:08
	 * @param ids
	 */
	public int batchDeleteStudent(String[] ids) {
		int result = 0;
		for (String id : ids) {
			this.deleteStudentById(id);
			result++;
		}
		return result;

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:55:43
	 * @param id
	 */
	public Map getStudentById(String id) {
		return this.studentDao.getStudentById(id);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 23:25:01
	 * @param student
	 */
	public int updateStudent(Student student) {
		return this.studentDao.updateStudent(student);

	}

	/**
	 * @author Po Kong
	 * @since 24 Jul 2012 22:21:51
	 */
	public List<Student> listAllStudent() {
		return this.studentDao.listAllStudent();

	}

}
