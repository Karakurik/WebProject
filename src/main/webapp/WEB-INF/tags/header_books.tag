<%@ tag description="header" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="header">
    <div class="logo">
        <img class="logo" src="<c:url value="/resources/images/book.jpg"/>" alt="Логотип" name="logo"/>
    </div>
    <div class="descr">
        <h3>
            Привет, друг! Это наша онлайн библиотека
            <c:if test="${requestScope.auth == true}">
                <a style="color: #84bbf3" href="logout">Выйти</a>
            </c:if>
            <br>
            <c:if test="${requestScope.isAdmin == true}">
                <a style="color: #84bbf3" href="crudPanel">АдминПанель</a>
            </c:if>
        </h3>
    </div>

    <div class="search_form">
        <form name="search_form" action="books" method="POST">
            <input type="text" name="search_string" value="${param.search_string}" size="110"/>
            <input class="search_button" type="submit" value="Поиск" name="search_button"/>
            <select name="search_option">
                <option>Название</option>
                <option>Автор</option>
            </select>
        </form>
    </div>
</div>