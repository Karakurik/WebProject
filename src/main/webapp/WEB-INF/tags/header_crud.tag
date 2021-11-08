<%@ tag description="header_crud" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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