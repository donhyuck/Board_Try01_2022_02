<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 조건문</title>
</head>
<body>
<!-- http://localhost:9100/Exam/jstl-discount.jsp -->
	<h3>할인 대상 판별</h3>

	<c:set var="age" value="26" />
	
	당신은 ${ age }세 입니다.
	
	<!-- if조건문 단독 -->
	<c:if test="${ age < 20 || age >= 60 }">
		<div>할인대상입니다.</div>
	</c:if>
	
	<c:if test="${ age >= 20 && age < 60 }">
		<div>할인대상이 아닙니다.</div>
	</c:if>
	
	<!-- if, else 같이 choose로 묶는다. -->
	<c:choose>
		<c:when test="${ age < 20 || age >= 60 }">
			<div>할인대상입니다.</div>
		</c:when>
		
		<c:otherwise>
			<div>할인대상이 아닙니다.</div>
		</c:otherwise>
	</c:choose>
	
	
	
</body>
</html>