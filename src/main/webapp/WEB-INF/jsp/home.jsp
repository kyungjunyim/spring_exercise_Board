<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>�ȳ��ϼ���</h1>
	<hr>
	<form action="/board/add" method="get">
	<div>
		<div>����</div>
		<input type="text" name="title">
	</div>
	<div>
		<div>����</div>
		<textarea name="contents"></textarea>
	</div>
	<button type="submit">����ϱ�</button>
	</form>
</body>
</html>