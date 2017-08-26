<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生列表</title>
</head>
<body>
	<h1>学生列表</h1>
	<div>
		<a href="add.jsp">添加学生信息</a>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>序号</th>
				<th>学号</th>
				<th>姓名</th>
				<th>年级</th>
				<th>性别</th>
				<th>出生年月</th>
				<th colspan="2">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pb.list }" var="stu" varStatus="status">
			<tr>
				<td>${status.index+1 }</td>
				<td>${stu.id }</td>
				<td>${stu.name }</td>
				<td>${stu.grade }</td>
				<td><c:if test="${stu.sex==1 }">男</c:if>
				<c:if test="${stu.sex==0 }">女</c:if>
				</td>
				<td>${stu.birthday }</td>
				<td><a href="student?option=showStu&id=${stu.id }">修改</a></td>
				<td><a href="student?option=del&id=${stu.id }">删除</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:set var="page" value="${pb }"></c:set>
	共有:${page.totalRecord }个学生,共:${page.totalPage }页,当前页:${page.pageNum }页<br/>
	 <a href="student?pageNum=1">首页</a>
	<c:if test="${page.pageNum>1 }">
		<a href="student?pageNum=${page.pageNum-1 }">上一页</a>
	</c:if>
	${page.pageNum }
	<c:if test="${page.pageNum<page.totalPage }">
		<a href="student?pageNum=${page.pageNum+1 }">下一页</a>
	</c:if>
	<a href="student?pageNum=${page.totalPage}">尾页</a>
</body>
</html>