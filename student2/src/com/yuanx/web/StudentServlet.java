package com.yuanx.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanx.model.PageBean;
import com.yuanx.model.Student;
import com.yuanx.service.StudentService;
import com.yuanx.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService studentService;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		studentService = new StudentServiceImpl();
	}

	public StudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户信息
		String option = request.getParameter("option");
		if (option == null || option.equals("")) {// 展示所有学生信息
			show(request, response);
		}
		if ("add".equals(option)) {// 新增学生信息
			add(request, response);
		}
		if ("del".equals(option)) {// 删除学生信息
			del(request, response);
		}
		if ("showStu".equals(option)) {// 查询具体学生信息
			showStu(request, response);
		}
		if ("update".equals(option)) {// 修改学生信息
			update(request, response);
		}
	}

	// 修改学生信息
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取参数
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String grade = request.getParameter("grade");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		// 调用service方法
		studentService.updateStudent(id, name, grade, sex, birthday);
		request.getRequestDispatcher("student?option=").forward(request, response);

	}

	// 展示具体学生信息
	private void showStu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Student student = studentService.findStudent(id);
		request.setAttribute("stu", student);
		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}

	private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		studentService.deleteStudent(id);
		request.getRequestDispatcher("student?option=").forward(request, response);

	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String grade = request.getParameter("grade");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		studentService.add(name, grade, sex, birthday);

		request.getRequestDispatcher("student?option=").forward(request, response);
	}

	// 分页展示展示学生信息
	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || "".equals(pageNum)) {
			pageNum = "1";
		}
		int newPageNum = Integer.parseInt(pageNum);
		int pageSize = 5;
		PageBean<Student> pb = studentService.findAllStudentWithPage(newPageNum, pageSize);
		request.setAttribute("pb", pb);
		request.getRequestDispatcher("students.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
