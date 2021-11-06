<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout_reg title="Онлайн библиотека::Регистрация">
    <div class="login_div">
        <p class="title">Регистрация:<br><a href="/login">Уже есть аккаунт?</a></p>
        <form class="login_form" method="post">
            <label class="registration-form-elem">
                <input type="text" name="name" placeholder="Имя" value="${param.name}" size="20">
            </label>
            <label class="registration-form-elem">
                <input type="email" name="email" placeholder="Email" value="${param.email}" size="20">
            </label>
            <label class="registration-form-elem">
                <input type="text" name="login" placeholder="Юзернейм" value="${param.login}" size="20">
            </label>
            <label class="registration-form-elem">
                <input type="password" name="password" placeholder="Пароль" value="password" size="20">
            </label>
            <label class="registration-form-elem">
                <input type="password" name="password_repeat" placeholder="Повторите пароль" value="password_repeat" size="20">
            </label>
            <p class="message">${message}</p>
            <label class="registration-form-elem">
                <input type="submit" value="Зарегистрироваться">
            </label>
        </form>
    </div>
</t:layout_reg>
