<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>JSP Test</h3>
	html작성 <br />

	<%
	// JSP를 작성하는 법.
    // <% 를 이용해서 HTML 코드와 JAVA 코드를 구분.
    // <% 표현을 스크립틀릿이라고 부름.
    // 추후 스크립틀릿으로 감싸여진 부분은 서블릿으로 변환할 때 일반 자바코드로 들어가고
    // HTML 코드는 out.write()로 변환되는 방식
	%>

	<!-- 자바코드를 적용할 수 있다. -->
	<%	for (int i = 1; i <= 3; i++) {	%>
	hello!	<br>
	<%	}	%>

</body>
</html>