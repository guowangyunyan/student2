package com.yuanx.service.impl;

import java.util.Date;
import java.util.List;

import com.yuanx.dao.StudentDao;
import com.yuanx.dao.impl.StudentDaoImpl;
import com.yuanx.model.PageBean;
import com.yuanx.model.Student;
import com.yuanx.service.StudentService;

public class StudentServiceImpl implements StudentService {
	private StudentDao studentDao;

	public StudentServiceImpl() {
		studentDao = new StudentDaoImpl();
	}

	// 分页展示所有学生信息
	@Override
	public PageBean<Student> findAllStudentWithPage(int newPageNum, int pageSize) {
		List<Student> students = studentDao.findAllStudent();
		int totalRecord = students.size();
		PageBean<Student> pb = new PageBean<Student>(newPageNum, pageSize, totalRecord);
		int startIndex = pb.getStartIndex();
		pb.setList(studentDao.findAll(startIndex, pageSize));
		return pb;
	}

	// 删除学生信息
	@Override
	public void deleteStudent(String id) {
		int studentId = Integer.parseInt(id);
		studentDao.deleteStudentById(studentId);
	}

	// 查询具体学生信息 为修改做准备
	@Override
	public Student findStudent(String id) {
		int studentId = Integer.parseInt(id);
		Student student = studentDao.findStudentById(studentId);
		return student;
	}

	// 修改学生信息
	@Override
	public void updateStudent(String id, String name, String grade, String sex, String birthday) {
		Student student = new Student();
		student.setId(Integer.parseInt(id));
		student.setName(name);
		student.setGrade(grade);
		student.setSex(Integer.parseInt(sex));
		student.setBirthday(new Date());
		studentDao.updateStudentById(student);
	}

	// 添加学生信息
	@Override
	public void add(String name, String grade, String sex, String birthday) {
		Student student = new Student();
		student.setName(name);
		student.setGrade(grade);
		student.setSex(Integer.parseInt(sex));
		student.setBirthday(new Date());
		studentDao.addStudent(student);
	}

}
