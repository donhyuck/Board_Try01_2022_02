<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- http://localhost:9100/Exam/tri.jsp?height=9 -->
	<h3>높이에 맞는 삼각형</h3>
	<%	int height = Integer.parseInt(request.getParameter("height"));	%>
	
	height : [ <%=height%> ]	<br />

	<% for (int j = 1; j <= height; j++) {
		for (int i = height; i >= 1; i--) {	%>
			<% if (i > j) {  %>
				 &nbsp;
			<% } else {  %>
				*
			<% }  %>
		<% } %>
		<br />
	<% } %>

</body>
</html>