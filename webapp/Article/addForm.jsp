<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
</head>
<body>
	<h3>게시글 등록</h3>
	
	<%@ include file="header.jspf" %>
	
	<form action="http://localhost:9100/article/add" method="POST">
		<div>
			제 목 : <input type="text" name="title"/> <br />
			내 용 : <input type="text" name="body"/> <br />
			작성자 : <input type="text" name="nickname"/> <br />
			<input type="submit" value="게시글 등록" />
		</div>
	</form>

</body>
</html>