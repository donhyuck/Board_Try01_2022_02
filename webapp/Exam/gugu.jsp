<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>응용 구구단</h3>
	<%
	// JSP는 실행시 톰캣에 내장되어 있는 Jasper 엔진에 의해 서블릿으로 변환되어 실행됨.
	// 이때 Jasper 엔진은 서블릿에서 사용되던 유용한 객체들을 만들어서 넣어주는데 이를 내장객체라고 함
	// 내장객체는 여러 종류가 있지만 대표적으로 다음이 많이 사용됨
	// 자바 변수를 출력할때, out.println(dan); 대신에 =%로 표현한다. 
	
	// request, response -> 요청과 응답 처리에 필요한 정보와 기능
	// out -> 응답 출력기(브라우저상에 출력)
	// param -> 요청 파라미터를 갈무리해서 저장해 놓음.
	%>

	<%
	int n = Integer.parseInt(request.getParameter("n"));
	int m = Integer.parseInt(request.getParameter("m"));
	int limit = Integer.parseInt(request.getParameter("limit"));

	for (int dan = n; dan <= m; dan++) {  %>
	
		=== <%=dan %>단 === <br />
		<% for (int i = 1; i <= limit; i++) {  %>

		<%=dan %> * <%=i %> = <%=dan * i %><br />

		<% } %>
		<hr>
		
	<% } %>

</body>
</html>