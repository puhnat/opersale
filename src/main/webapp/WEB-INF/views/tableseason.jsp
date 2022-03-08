<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
    </head>
    <body>
        <h1>Таблица сезонов</h1>

        <br />
        <a href="/seasons/?view=listseason">Отобразить списком</a>
        <br />

        <table border="1px">
            <c:forEach var="obj" items="${objects}">
                <tr>
                    <td><a href="/seasons/${obj.id}">${obj.name}</a></td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>

