<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 등록</title>
</head>
<body>
	<h3>주소록 등록</h3>

<!-- input테그 내 name은 request.getParameter의 설정명과 동일해야한다. -->
	<form action="http://localhost:9100/addr/add">
		<div>
			이름 : <input type="text" name="name"/> <br />
			주소 : <input type="text" name="address"/> <br />
			번호 : <input type="text" name="phone"/> <br />
			<input type="submit" value="주소록 등록" />
		</div>
	</form>

</body>
</html>