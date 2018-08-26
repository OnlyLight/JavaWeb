<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="_header.jsp"></jsp:include>
	<h2>Music Store</h2>

	<div>
		<h3>Demo content</h3>

		<ul>
			<li><a href="/MusicStore/product-list">Music List</a></li>
			<li><a href="/MusicStore/admin">Admin pages</a></li>
			<li><a href="/MusicStore/login">Login</a></li>
		</ul>
	</div>

	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>