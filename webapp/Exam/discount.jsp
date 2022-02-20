<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- http://localhost:9100/Exam/discount.jsp?age=34 -->
	<h3>할인 대상 판별</h3>

	<%	int age = Integer.parseInt(request.getParameter("age"));	%>
	당신은 <% out.print(age); %>세 입니다.
	
	<%	if (age < 20 || age > 60) {	%>
	
	<div>할인대상입니다.</div>
	
	<%	}else {	%>
	
	<div>할인대상이 아닙니다.</div>
	
	<%	}	%>
	
</body>
</html>