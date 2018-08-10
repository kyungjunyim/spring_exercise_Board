<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>안녕하세요</h1>
	<hr>
	<form action="/board/add" method="get">
	<div>
		<div>제목</div>
		<input type="text" name="title">
	</div>
	<div>
		<div>내용</div>
		<textarea name="contents"></textarea>
	</div>
	<button type="submit">등록하기</button>
	</form>
</body>
</html>