<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>
<body>
	<h3>게시글 상세</h3>
	
	<%@ include file="header.jspf" %>

	<div>
		번 호 : ${ article.idx }		 <br />
		등록일 : ${ article.regDate }	 <br />
		제 목 : ${ article.title }	 <br />
		내 용 : ${ article.body }		 <br />
		작성자 : ${ article.nickname } <br />
	</div>
	<hr />
	<a href="http://localhost:9100/article/list">목록으로 이동</a>
	
	<!-- 해당 번호를 같이 쿼리스트링으로 넘긴다. -->
	<!-- 수정 기능의 경우 a테그로 처리가능하지만 삭제기능과 양식을 통일하고자 form테그로 처리 -->
	<!-- <a href="/article/showUpdateForm?idx=${ article.idx }">수정</a>  -->	
	<!-- 삭제 기능은 POST 메서드로 처리해야한다. -->
	<div>
		<form action="/article/showUpdateForm">
			<input type="hidden" name="idx" value="${ article.idx }"/>
			<input type="submit" value="수정"/>
		</form>
		<form action="/article/delete" method="POST">
			<input type="hidden" name="idx" value="${ article.idx }"/>
			<input type="submit" value="삭제"/>
		</form>
	</div>
	
	<hr />
	<h4> 댓글 </h4>
	
	<!-- 댓글 작성구역 -->
	<div>
		<form action="/article/addReply" method="POST">
			작성자 : ${ loginedUserName }	<br />
			<input type="text" name="body" placeholder="여기에 댓글을 입력하세요." />
			<input type="hidden" name="articleIdx" value="${ article.idx }" />
			<input type="hidden" name="nickname" value="${ loginedUserName }" />
			<input type="submit" value="댓글 남기기"/>
			<hr />
		</form>
	</div>
	
	<!-- 댓글 목록 -->
	<c:forEach items="${ replies }" var="reply">
		<div>
			${ reply.nickname }		<br />
			${ reply.body }			<br />
			${ reply.regDate }		<br />
			
			<!-- 댓글 수정 및 삭제 -->
			<div>
				<form action="/article/showReplyForm" method="GET">
					<input type="hidden" name="idx" value="${ reply.idx }" />
					<input type="submit" value="수정" />
				</form>
				<form action="/article/doReplyDelete" method="POST">
					<input type="hidden" name="idx" value="${ reply.idx }" />
					<input type="hidden" name="articleIdx" value="${ reply.articleIdx }" />
					<input type="submit" value="삭제" />
				</form>
			</div>
		</div>
		<hr />
	</c:forEach>
	
	

</body>
</html>