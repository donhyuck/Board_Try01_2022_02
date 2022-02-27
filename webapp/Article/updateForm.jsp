<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
<!-- 수정할때 이전 등록내용을 불러와서 이를 수정해야 한다. -->
	<h3>게시글 수정</h3>

	<form action="http://localhost:9100/article/update" method="POST">
		<div>
			제 목 : <input type="text" name="title" value="${ article.title }"/> <br />
			내 용 : <input type="text" name="body" value="${ article.body }"/> <br />
			<input type="hidden" name="idx" value="${ article.idx }"/> <br />
			<input type="submit" value="게시글 수정" />
		</div>
	</form>

</body>
</html>