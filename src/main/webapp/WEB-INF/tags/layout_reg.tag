<%@ tag description="layout_reg" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ attribute name="title" required="true" %>
<html>
<head>
    <title>${title}</title>
    <t:head_reg/>
</head>
<body>
<div class="main">
    <t:header_reg/>
    <jsp:doBody/>
</div>
</body>
</html>