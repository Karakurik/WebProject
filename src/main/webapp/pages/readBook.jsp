<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="<c:url value="/js/readFile.js"/>"></script>
    <title></title>
</head>
<body>
    <script type="text/javascript">
        test()
        readFiles(${content})
    </script>
</body>
</html>
