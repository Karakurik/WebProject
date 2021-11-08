<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>

<head>
    <title>Админ Панель</title>
    <script type="text/javascript" src="/js/confirm.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<t:header_crud/>
<br>

<div class="row">
    <div class="container">
        <h3 class="text-center">List of Book</h3>
        <hr>
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/crudPanel/new" class="btn btn-success">Add
                New Book</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Page Count</th>
                <th>Isbn</th>
                <th>Genre</th>
                <th>Author</th>
                <th>Publish Date</th>
                <th>Publisher</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${listBook}">
                <tr>
                    <td>
                        <c:out value="${book.getId()}"/>
                    </td>
                    <td>
                        <c:out value="${book.getName()}"/>
                    </td>
                    <td>
                        <c:out value="${book.getPageCount()}"/>
                    </td>
                    <td>
                        <c:out value="${book.getIsbn()}"/>
                    </td>
                    <td>
                        <c:out value="${book.getGenre()}"/>
                    </td>
                    <td>
                        <c:out value="${book.getAuthor()}"/>
                    </td>
                    <td>
                        <c:out value="${book.getPublishDate()}"/>
                    </td>
                    <td>
                        <c:out value="${book.getPublisher()}"/>
                    </td>
                    <td><a href="crudPanel/edit?id=<c:out value='${book.getId()}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
                        <a class="confirmation" href="crudPanel/delete?id=<c:out value='${book.getId()}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>