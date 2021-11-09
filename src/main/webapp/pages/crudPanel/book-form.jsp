<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout_crud title="Админ Панель">
    <br>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <c:if test="${update}">
                <form action="<c:url value="/crudPanel/update"/>" method="post">
                    </c:if>
                    <c:if test="${addNew}">
                    <form action="<c:url value="/crudPanel/insert"/>" method="post" <%--enctype="multipart/form-data"--%>>
                        </c:if>
                        <caption>
                            <h2>
                                <c:if test="${update}">
                                    Edit Book
                                </c:if>
                                <c:if test="${addNew}">
                                    Add New Book
                                </c:if>
                            </h2>
                        </caption>
                        <c:if test="${update}">
                            <input type="hidden" name="id" value="<c:out value='${book.getId()}' />"/>
                        </c:if>

                        <fieldset class="form-group">
                            <label>Name</label> <input type="text" value="<c:out value='${book.getName()}' />"
                                                       class="form-control" name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Page Count</label> <input type="number"
                                                             value="<c:out value='${book.getPageCount()}' />"
                                                             class="form-control" name="page_count" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Isbn</label> <input type="text" value="<c:out value='${book.getIsbn()}' />"
                                                       class="form-control" name="isbn">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Genre</label> <input type="text" value="<c:out value='${book.getGenre()}' />"
                                                        class="form-control" name="genre">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Author</label> <input type="text" value="<c:out value='${book.getAuthor()}' />"
                                                         class="form-control" name="author">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Publish Year</label> <input type="number"
                                                               value="<c:out value='${book.getPublishDate()}' />"
                                                               class="form-control" name="publish_date">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Publisher</label> <input type="text" value="<c:out value='${book.getPublisher()}' />"
                                                            class="form-control" name="publisher">
                        </fieldset>

                            <%--<fieldset class="form-group">
                                <label>Content</label> <input type="file" name="file">
                            </fieldset>--%>

                        <p style="color: red" class="message">${message}</p>

                        <button type="submit" class="btn btn-success">Save</button>
                    </form>
            </div>
        </div>
    </div>
</t:layout_crud>
