<%@ tag import="ru.kpfu.webproject.fayzrakhmanov.entity.Genre" %>
<%@ tag description="lef_menu" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<div class="sidebar1">
    <h4>Жанры:</h4>
    <ul class="nav">
        <jsp:useBean id="genreList" class="ru.kpfu.webproject.fayzrakhmanov.controllers.GenreListDaService" scope="application"/>
        <c:forEach items="${genreList.getGenreList()}" var="genre">
            <li><a href="<c:url value="books?genre_id=${genre.getId()}&name=${genre.getName()}"/>">${genre.getName()}</a></li>
        </c:forEach>
    </ul>
</div>
