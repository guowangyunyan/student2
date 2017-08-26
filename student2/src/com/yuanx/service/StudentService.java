package com.yuanx.service;

import com.yuanx.model.PageBean;
import com.yuanx.model.Student;

public interface StudentService {

	void deleteStudent(String id);

	Student findStudent(String id);

	void updateStudent(String id, String name, String grade, String sex, String birthday);

	void add(String name, String grade, String sex, String birthday);

	PageBean<Student> findAllStudentWithPage(int newPageNum, int pageSize);

}
