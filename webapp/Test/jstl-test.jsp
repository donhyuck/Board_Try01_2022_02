<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jstl tag 라이브러리 uri의 기능을 쓰려면 접두어 c를 붙여한다.-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	for (int i = 1; i <= 3; i++) {
		out.println(i);
	}
%>
	<hr />
<%
	ArrayList<String> list = new ArrayList<>();
	
	list.add("aaa");
	list.add("bbb");
	list.add("ccc");
	
	for(String str : list) {
		out.println(str);
	}
	
	// ArrayList<String> getList = (ArrayList<String>) request.getAttribute("list");
	// jstl과 el표현을 사용할 경우 list를 사용하지 않아도 데이터를 불러올 수 있다.
	// 테스트를 위해 여기서 attribute를 선언했지만, 서블릿을 통해 jsp가 받게된다.
	request.setAttribute("list", list);
	
%>
	<hr />

	<c:forEach var="i" begin="1" end="3" step="1">
		${ i }
	</c:forEach>
	<hr />
	
	<!-- EL표현으로 자바 변수를 가져다 쓸 수 있다.
	단, ArrayList<String> list 에서 쓰는 것이 아닌
	request에 attribute로 들어있는 값을 사용한다. -->
	<c:forEach items="${list}" var="str">
		${ str }
	</c:forEach>

</body>
</html>