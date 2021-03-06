<%@ tag description="layout" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ attribute name="title" required="true" %>
<html>
<head>
    <t:head_books/>
    <title>${title}</title>
</head>
<body>
<div class="container">
    <t:header_books/>
    <t:left_menu/>
    <jsp:doBody/>
    <t:footer/>
</div>
</body>
</html>
