<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加学生信息</title>
</head>
<body>
	<h2>添加学生信息</h2>
	<hr>
		<a href="student">返回学生列表</a>
	<hr>
<form action="student" method="post">
<input type="hidden" name="option" value="add">
姓名:<input type="text" name="name"><br>
年级:<input type="text" name="grade"><br>
性别:<input type="radio" name="sex" value="1">男
<input type="radio" name="sex" value="0">女<br>
出生日期:<input type="datetime" name="birthday" ><br>

<input type="submit" value="新增">


</form>
</body>
</html>