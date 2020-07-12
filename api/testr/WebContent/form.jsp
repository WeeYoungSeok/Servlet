<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
span {
	width: 60px;
	display: inline-block;
}

textarea {
	width: 40%;
	height: 280px;
}
</style>
</head>
<body>
<h2>kakao</h2>

	<form action="index.jsp">

		<p>
			<span>name:</span> <input name="name">


			<!-- 값 가져오기 -->
		</p>
		<p>
			<span>이메일:</span> <input name="email">
		</p>
		<span>폰넘버:</span>
		<p>
			<input type="text" name="phone">
		</p>
		<span>주소:</span>
		<p>
			<input name="address">
		</p>
		<span>총가격:</span>
		<p>
			<input name="totalPrice">
		</p>
		<input type="submit" value="결제하기"> <input type="reset"
			value="취소하기">
	</form>
</body>
</html>