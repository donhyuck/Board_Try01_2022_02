<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
</head>
<body>
	<h3> 댓글 수정 </h3>
	<form action="/article/doReplyUpdate" method="POST">
		<input type="hidden" name="idx" value="${ reply.idx }" />
		<input type="hidden" name="articleIdx" value="${ reply.articleIdx }" />
		<input type="hidden" name="nickname" value="${ loginedUserName }" />
		${ loginedUserName }	<br />
		<input type="text" name="body" value="${ reply.body }" />
		<input type="submit" value="등록" />
	</form>

</body>
</html>