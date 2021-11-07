<%--
  Created by IntelliJ IDEA.
  User: insaf
  Date: 06.11.2021
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
  <title>User Management Application</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
  <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
    <div>
      <a href="/crudPanel" class="navbar-brand"> Book Management App </a>
    </div>

    <ul class="navbar-nav">
      <li><a href="<%=request.getContextPath()%>/crudPanel/list" class="nav-link">Books</a></li>
    </ul>
  </nav>
</header>
<br>
<div class="container col-md-5">
  <div class="card">
    <div class="card-body">
      <c:if test="${book != null}">
      <form action="crudPanel/update" method="post">
        </c:if>
        <c:if test="${book == null}">
        <form action="crudPanel/insert" method="post">
          </c:if>

          <caption>
            <h2>
              <c:if test="${book != null}">
                Edit Book
              </c:if>
              <c:if test="${book == null}">
                Add New Book
              </c:if>
            </h2>
          </caption>
          <c:if test="${book != null}">
            <input type="hidden" name="id" value="<c:out value='${book.getId()}' />" />
          </c:if>

          <fieldset class="form-group">
            <label>Name</label> <input type="text" value="<c:out value='${book.getName()}' />" class="form-control" name="name" required="required">
          </fieldset>

          <fieldset class="form-group">
            <label>Page Count</label> <input type="number" value="<c:out value='${book.getPageCount()}' />" class="form-control" name="page_count" required="required">
          </fieldset>

            <fieldset class="form-group">
              <label>Isbn</label> <input type="text" value="<c:out value='${book.getIsbn()}' />" class="form-control" name="isbn">
            </fieldset>

          <fieldset class="form-group">
            <label>Genre</label> <input type="text" value="<c:out value='${book.getGenre()}' />" class="form-control" name="genre">
          </fieldset>

          <fieldset class="form-group">
            <label>Author</label> <input type="text" value="<c:out value='${book.getAuthor()}' />" class="form-control" name="author">
          </fieldset>

          <fieldset class="form-group">
            <label>Publish Year</label> <input type="number" value="<c:out value='${book.getDate()}' />" class="form-control" name="publish_date">
          </fieldset>

          <fieldset class="form-group">
            <label>Publisher</label> <input type="text" value="<c:out value='${book.getPublisher()}' />" class="form-control" name="publisher">
          </fieldset>

          <button type="submit" class="btn btn-success">Save</button>
        </form>
    </div>
  </div>
</div>
</body>

</html>
