<%@ tag description="header" pageEncoding="UTF-8" %>

<div class="header">
    <div class="logo">
        <img class="logo" src="/resources/images/book.jpg" alt="Логотип" name="logo" />

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