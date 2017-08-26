package com.yuanx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.yuanx.dao.StudentDao;
import com.yuanx.model.Student;
import com.yuanx.util.DBUtil;

public class StudentDaoImpl implements StudentDao {

	@Override
	public List<Student> findAllStudent() {
		List<Student> students = null;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from tb_student";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			students = new ArrayList<Student>();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setGrade(rs.getString("grade"));
				student.setSex(rs.getInt("sex"));
				student.setBirthday(rs.getTimestamp("birthday"));
				students.add(student);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return students;

	}

	@Override
	public List<Student> findAll(int startIndex, int pageSize) {
		List<Student> students = null;
		String sql = "select * from tb_student limit ?,?";
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, startIndex);
			ps.setInt(2, pageSize);
			ps.executeQuery();
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			students = new ArrayList<Student>();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setGrade(rs.getString("grade"));
				student.setSex(rs.getInt("sex"));
				student.setBirthday(rs.getTimestamp("birthday"));
				students.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;

	}

	@Override
	public boolean deleteStudentById(int id) {
		Connection connection = DBUtil.getConn();
		String sql = "delete from tb_student where id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			int rs = ps.executeUpdate();
			if (rs > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Student findStudentById(int id) {
		Student student = new Student();
		Connection connection = DBUtil.getConn();
		String sql = "SELECT * FROM tb_student WHERE id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setGrade(rs.getString("grade"));
				student.setSex(rs.getInt("sex"));
				student.setBirthday(rs.getDate("birthday"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return student;
	}

	@Override
	public boolean updateStudentById(Student student) {
		Connection connection = DBUtil.getConn();
		String sql = "update tb_student set name=?,grade=?,sex=?,birthday=? where id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setString(2, student.getGrade());
			ps.setInt(3, student.getSex());
			ps.setTimestamp(4, new Timestamp(student.getBirthday().getTime()));
			ps.setInt(5, student.getId());
			int rs = ps.executeUpdate();
			if (rs > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addStudent(Student student) {
		Connection connection = DBUtil.getConn();
		String sql = "INSERT INTO tb_student (name,grade,sex,birthday) VALUES(?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setString(2, student.getGrade());
			ps.setInt(3, student.getSex());
			ps.setTimestamp(4, new Timestamp(student.getBirthday().getTime()));
			int rs = ps.executeUpdate();
			if (rs > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
