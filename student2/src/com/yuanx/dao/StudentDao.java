package com.yuanx.dao;

import java.util.List;

import com.yuanx.model.Student;

public interface StudentDao {

	List<Student> findAllStudent();

	List<Student> findAll(int startIndex, int pageSize);

	boolean deleteStudentById(int studentId);

	Student findStudentById(int studentId);

	boolean updateStudentById(Student student);

	boolean addStudent(Student student);

}
