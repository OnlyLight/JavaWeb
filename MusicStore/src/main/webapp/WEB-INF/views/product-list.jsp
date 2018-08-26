<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<c:forEach var="list" items="${ list }">
		<a href="/MusicStore/buyProduct?code=${ list.code }">
			<ul style="list-style-type: none">
				<li><c:out value="${ list.code }" /></li>
				<li><c:out value="${ list.name }" /></li>
			</ul>
		</a>
	</c:forEach>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>