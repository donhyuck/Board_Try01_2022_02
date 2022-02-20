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
<%
	AddrDB db = new AddrDB();
	ArrayList<Addr> addrList = db.selectDatas();
%>	
	<h3> 주소 목록 </h3>
	<% for (int i = 0; i < addrList.size(); i++) { 
	
		Addr addr = addrList.get(i);
	%>
	
	<div>
		번호 : <%= addr.getIdx() %> <br />
		이름 : <%= addr.getName() %> <br />
		주소 : <%= addr.getAddress() %> <br />
		번호 : <%= addr.getPhone() %> <br />
	</div>
	<hr />
	
	<% } %>

</body>
</html>