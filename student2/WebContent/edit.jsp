<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改学生信息</title>
</head>

<body>
	<h2>修改学生信息</h2>
	<hr>
		<a href="student">返回学生列表</a>
	<hr>
	<c:set var="gander" value="true" />
	<c:if test="${stu.sex == 0 }">
		<c:set var="sex" value="false" />
	</c:if>
		<form action="student" method="post">
			<input type="hidden" name="option" value="update">
			<input type="hidden" name="id" value="${stu.id }"/>
			姓名:<input type="text" name="name" value="${stu.name }"><br>
			年级:<input type="text" name="grade" value="${stu.grade }"><br>
		        性别:<input type="radio" name="sex" ${sex?'checked':'' }  value="1"/>男
			   <input type="radio" name="sex" ${!sex?'checked':'' } value="0"/>女<br>
			出生日期:<input type="datetime" name="birthday" value="${stu.birthday }"><br>
			
			<input type="submit" value="修改">
		
		
		</form>

</body>
</html>