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
	<form action="upMusic" method="post">
		<input type="text" placeholder="Name">
		<input type="text" placeholder="Price">
	</form>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>