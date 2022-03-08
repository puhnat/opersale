<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
    </head>
    <body>
        <h1>Новый сезон :</h1>
        <form method="POST" action="/seasons/new" id="newSeason" name="newSeason" enctype="multipart/form-data">
            <input type="number" id="id" name="id" value="id" hidden>
            <input type="text" id="name" name="name" value="Название сезона">
            <br />
            <input type="submit" id="btnAddSeason" name="btnAddSeason" value="Add New Season" />
        </form>
    </body>
</html>
