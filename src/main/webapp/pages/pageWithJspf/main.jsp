<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ru.kpfu.webproject.fayzrakhmanov.entity.Author"%>
<%@page import="ru.kpfu.webproject.fayzrakhmanov.controllers.AuthorListDaService"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Онлайн библиотека</title>
        <link href="/resources/css/mainPageStyle.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <div class="container">

            <div class="header">
                <div class="logo">
                    <img src="/resources/images/library.png" alt="Логотип" name="logo" />

                </div>
                <div class="descr">
                    <h3>Привет, друг! Это наша онлайн библиотека ИТИС</h3>
                </div>
                <div class="search_form">
                    <form name="search_form" method="POST">
                        <input type="text" name="search_String" value="" size="110" />
                        <input class="search_button" type="submit" value="Поиск" name="search_button" />
                        <select name="search_option">
                            <option>Название</option>
                            <option>Автор</option>
                        </select>
                    </form>
                </div>
            </div>

            <div class="sidebar1">
                <h4>Список авторов:</h4>
                <ul class="nav">
                    <jsp:useBean id="authorList" class="ru.kpfu.webproject.fayzrakhmanov.controllers.AuthorListDaService" scope="application"/>
                    <c:forEach var="author" items="${authorList.getAuthorList()}">
                        <li><a href="#">${author.name}</a></li>
                    </c:forEach>
                </ul>
                <p>&nbsp;</p>
            </div>


            <div class="content">
                <h1>&nbsp;</h1>
                <p>&nbsp;</p>
            </div>

        </div><!-- end .container -->

    </body>--%>





<%--<head>
    <title>Title</title>
</head>
<body>
    &lt;%&ndash;Для того, чтобы переданные параметры отобажались в правильной кодировке&ndash;%&gt;
    <% request.setCharacterEncoding("UTF-8"); %>


    <h3>
        &lt;%&ndash;Вставка Java кода&ndash;%&gt;
        <%= "Привет, " + request.getParameter("login") + ", это главная страница нашей библиотеки"%>
    </h3>

    <h3>
        &lt;%&ndash;То же самое значение при помощи EL (expression language), чаще используется JSF&ndash;%&gt;
        ${param["login"]}
    </h3>
</body>--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%--<!DOCTYPE html>--%>


<%--<%@include file="/WEB-INF/tags/left_menu.tag" %>--%>
<%--<t:left_menu/>--%>
<t:layout_books title="Главная">

</t:layout_books>