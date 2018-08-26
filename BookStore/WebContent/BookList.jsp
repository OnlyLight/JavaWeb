<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Books Store Application</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
	<style type="text/css">
		.center {
		    text-align: center;
		    font-family: 'Pacifico', cursive;
		}
		
		.btn-custom {
		    cursor: pointer;
		}
		
		.center h2 a {
			color: #8b9ba3;
			text-decoration: none;
		}
		
		.center h2 a:hover {
			color: #616161;
		}
		
		.list-custom {
			text-decoration: none;
			color: #F44336;
		}
		
		.list-custom:hover {
			text-decoration: none;
			color: #B71C1C;
		}
	</style>
</head>
<body>
	<jsp:include page="common/header.jsp"></jsp:include>
    <div class="container center" align="center">
        <table class="table table-dark table-striped table-hover">
            <caption><h2 align="center">List of Books</h2></caption>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="book" items="${listBook}">
                <tr>
                    <td><c:out value="${book.id}" /></td>
                    <td><c:out value="${book.title}" /></td>
                    <td><c:out value="${book.author}" /></td>
                    <td><c:out value="${book.price}" /></td>
                    <td>
                    	<a class="list-custom" href="/BookStore/edit?id=<c:out value='${book.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a class="list-custom" href="/BookStore/delete?id=<c:out value='${book.id}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
        <button class="btn btn-danger btn-custom"><a style="color: white; text-decoration: none;" href="/BookStore/logout">Logout</a></button>
    </div>	
</body>
</html>