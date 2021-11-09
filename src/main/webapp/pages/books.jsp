<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout_books title="Книги">
    <div class="book_list">
        <c:if test="${!list.isEmpty()}">
            <h3>${param.name}</h3>
        </c:if>
        <c:if test="${list.isEmpty()}">
            <h3 style="color:red;">По запросу "${param.name}${param.search_string}" книги не найдены</h3>
        </c:if>
        <c:forEach var="book" items="${list}">
            <div class="book_info">
                <div class="book_title">
                    <p>${book.getName()}</p>
                </div>
                <div class="book_image">
                    <img src="${request.getContextPath()}/ShowImageServlet?index=${list.indexOf(book)}" height="250"
                         width="190" alt="Обложка"/>
                </div>
                <div class="book_details">
                    <br><strong>ISBN:</strong> ${book.getIsbn()}
                    <br><strong>Издательство:</strong> ${book.getPublisher()}

                    <br><strong>Количество страниц:</strong> ${book.getPageCount()}
                    <br><strong>Год издания:</strong> ${book.getPublishDate()}
                    <br><strong>Автор:</strong> ${book.getAuthor()}
                    <p style="margin:10px;"><a href="readBook?id=${book.getId()}">Читать</a></p>
                </div>
            </div>
        </c:forEach>
    </div>
</t:layout_books>
