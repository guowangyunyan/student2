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
import com.yuanx.util.StringUtil;

public class StudentDaoImpl implements StudentDao {

	@Override
	public List<Student> findAllStudent() {
		List<Student> students = null;
		Connection conn = DBUtil.getConn();
		String sql = "select * from tb_student";
		PreparedStatement ps = DBUtil.getPreparedStatement(conn, sql);
		ResultSet rs = DBUtil.getResultSet(ps);
		if (rs == null) {
			return null;
		}
		students = new ArrayList<Student>();
		try {
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
		} finally {

			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.colse(conn);
		}
		return students;

	}

	@Override
	public List<Student> findAll(int startIndex, int pageSize) {
		List<Student> students = null;
		String sql = "select * from tb_student limit ?,?";
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = DBUtil.getPreparedStatement(conn, sql);
		ResultSet rs = null;
		try {
			ps.setInt(1, startIndex);
			ps.setInt(2, pageSize);
			ps.executeQuery();
			rs = DBUtil.getResultSet(ps);
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
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.colse(conn);
		}
		return students;
	}

	@Override
	public boolean deleteStudentById(int id) {
		Connection conn = DBUtil.getConn();
		String sql = "delete from tb_student where id=?";
		PreparedStatement ps = DBUtil.getPreparedStatement(conn, sql);
		try {
			ps.setInt(1, id);
			int rs = ps.executeUpdate();
			if (rs > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.colse(conn);
		}
		return false;
	}

	@Override
	public Student findStudentById(int id) {
		Student student = new Student();
		Connection conn = DBUtil.getConn();
		String sql = "SELECT * FROM tb_student WHERE id=?";
		PreparedStatement ps = DBUtil.getPreparedStatement(conn, sql);
		try {
			ps.setInt(1, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = DBUtil.getResultSet(ps);
		try {
			while (rs.next()) {

				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setGrade(rs.getString("grade"));
				student.setSex(rs.getInt("sex"));
				student.setBirthday(rs.getDate("birthday"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.colse(conn);
		}
		return student;
	}

	@Override
	public boolean updateStudentById(Student student) {
		Connection conn = DBUtil.getConn();
		String sql = "update tb_student set name=?,grade=?,sex=?,birthday=? where id=?";
		PreparedStatement ps = DBUtil.getPreparedStatement(conn, sql);
		try {
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
		} finally {
			DBUtil.close(ps);
			DBUtil.colse(conn);
		}
		return false;
	}

	@Override
	public boolean addStudent(Student student) {
		Connection conn = DBUtil.getConn();
		String sql = "INSERT INTO tb_student (name,grade,sex,birthday) VALUES(?,?,?,?)";
		PreparedStatement ps = DBUtil.getPreparedStatement(conn, sql);
		try {
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

	@Override
	public List<Student> findStudentTotalRecord(String name, int sex) {
		List<Student> students = null;
		students = new ArrayList<Student>();
		Connection conn = DBUtil.getConn();
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT * FROM tb_student WHERE ");
		sb.append(" 1=1 ");
		if (!StringUtil.isEmpty(name)) {
			sb.append("and name=? ");
		}
		if (2 != sex) {
			sb.append("and sex=? ");
		}
		PreparedStatement ps = DBUtil.getPreparedStatement(conn, sb.toString());
		int index = 0;
		if (!StringUtil.isEmpty(name)) {
			try {
				ps.setString(++index, name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (2 != sex) {
			try {
				ps.setInt(++index, sex);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ResultSet rs = DBUtil.getResultSet(ps);
		if (rs == null) {
			return null;
		}
		try {
			while (rs.next()) {
				Student stu = new Student();
				stu.setBirthday(rs.getTimestamp("birthday"));
				stu.setSex(rs.getInt("sex"));
				stu.setGrade(rs.getString("grade"));
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				students.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.colse(conn);
		}
		return students;
	}

	@Override
	public List<Student> findStudent(String name, int sex, int startIndex, int pageSize) {
		List<Student> students = null;
		students = new ArrayList<Student>();
		Connection conn = DBUtil.getConn();
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT * FROM tb_student WHERE ");
		sb.append(" 1=1 ");
		if (!StringUtil.isEmpty(name)) {
			sb.append("and name=? ");
		}
		if (2 != sex) {
			sb.append("and sex=? ");
		}
		sb.append("LIMIT ?,?");
		PreparedStatement ps = DBUtil.getPreparedStatement(conn, sb.toString());
		int index = 0;
		if (!StringUtil.isEmpty(name)) {
			try {
				ps.setString(++index, name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (2 != sex) {
			try {
				ps.setInt(++index, sex);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			ps.setInt(++index, startIndex);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ps.setInt(++index, pageSize);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = DBUtil.getResultSet(ps);
		if (rs == null) {
			return null;
		}
		try {
			while (rs.next()) {
				Student stu = new Student();
				stu.setBirthday(rs.getTimestamp("birthday"));
				stu.setSex(rs.getInt("sex"));
				stu.setGrade(rs.getString("grade"));
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				students.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.colse(conn);
		}
		return students;
	}
}
