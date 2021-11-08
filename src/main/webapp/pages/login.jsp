<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout_reg title="Онлайн библиотека::Вход">
    <div class="login_div">
        <p class="title">Вход:<br><a href="/registration">Создать аккаунт?</a></p>
        <form class="login_form" name="username" method="POST">
            <input type="text" name="login" value="" size="20" placeholder="Логин или email" />
            <br>
            <input type="password" name="password" value="" size="20" placeholder="Пароль" />
            <br>
            <p class="message">${message}</p>
            <input type="submit" value="Войти" />
        </form>
    </div>
</t:layout_reg>
