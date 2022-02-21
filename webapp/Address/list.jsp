<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.don.address.Addr"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.don.address.AddrDB"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 목록</title>
</head>
<body>

	<h3>주소 목록</h3>
	<%
	ArrayList<Addr> addrList = (ArrayList<Addr>) request.getAttribute("addrList");

	for (Addr addr : addrList) {

	%>
	
	<div>
		번호 : <%= addr.getIdx() %>	<br />
		이름 : <%= addr.getName() %>	<br />
		주소 : <%= addr.getAddress() %>	<br />
		번호 : <%= addr.getPhone() %>	<br />
	</div>
	<hr />

	<% } %>

</body>
</html>