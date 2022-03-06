<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h3>로그인</h3>
	
	<form action="/member/login.do" method="POST">
		<div>
			아이디 : <input type="text" name="loginId"/>		<br />
			비밀번호 : <input type="password" name="loginPw"/>	<br />
			<input type="submit" value="로그인"/>	
		</div>
	</form>
	
</body>
</html>