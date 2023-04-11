<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=UTF-8">
<title>글 목록</title>
</head>
<body>
<center>
	<table border="1" cellpadding="0" cellspacing="0" width="700"> <!-- 뷰를 리턴하므로 포스트맨 사용 불가 -->
		<tr><th bgcolor="orange" width="100">번호</th><th bgcolor="orange" width="400">제목</th><th bgcolor="orange" width="150">작성자</th><th bgcolor="orange" width="500">내용</th><th bgcolor="orange" width="150">작성일</th><th bgcolor="orange" width="100">조회수</th></tr>
		<c:forEach var="board" items="${boardList }"> <!-- items는 컨트롤러의 addAttribute에서 보낸걸 받음 -->
		<tr>
			<td>${board.seq }</td>
			<td align="left"><a href="getBoard?seq=${board.seq}">${board.title }</td>
			<td>${board.writer }</td>
			<td>${board.content }</td>
			<td><fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
			<td>${board.cnt }</td>
		</tr>
		</c:forEach>
	</table>
	<br>
	<a href="insertBoard">새글 등록</a>
</center>
</body>
</html>