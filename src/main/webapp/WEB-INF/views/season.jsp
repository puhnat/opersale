<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
    </head>

    <body>
        <h1>${season}</h1>
        <ul type="square">
            <c:forEach var="premier" items="${premiers}">
                <li>${premier.name}</li>
            </c:forEach>
        </ul>
    </body>
</html>

