<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.don.address.Addr"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.don.address.AddrDB"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 목록</title>
</head>
<body>

	<h3>주소 목록</h3>

	<c:forEach items="${ addrList }" var="addr">
		<div>
			번호 : ${ addr.idx }		<br />
			이름 : ${ addr.name }		<br />
			주소 : ${ addr.address }	<br />
			번호 : ${ addr.phone }	<br />
		</div>
		<hr />
	</c:forEach>

</body>
</html>