<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>


    </head>

    <body>
        <h1>Список премьер :</h1>
        <table border="1px">
            <c:forEach var="premier" items="${premiers}">
                <tr>
                    <td>
                            ${premier.season.name}
                    </td>
                    <td>
                            ${premier.name}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

