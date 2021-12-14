<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>
    <title>Accident</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="card" style="width: 100%">
            <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse" id="navbarText">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link" href="/accident/">Главная</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/accident/create">Добавить инцидент</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>

        <div class="card-body">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Номер</th>
                    <th scope="col">Название</th>
                    <th scope="col">Тип</th>
                    <th scope="col">Статьи</th>
                    <th scope="col">Адрес</th>
                    <th scope="col">Описание</th>
                    <th scope="col">Изменить</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="accident" items="${accidents}">
                    <tr>
                        <td><c:out value="${accident.id}"/></td>
                        <td><c:out value="${accident.name}"/></td>
                        <td><c:out value="${accident.type.name}"/></td>
                        <td>
                            <c:forEach var="rule" items="${accident.rules}">
                                <c:out value="${rule.name}"/>
                            </c:forEach>
                        </td>
                        <td><c:out value="${accident.address}"/></td>
                        <td><c:out value="${accident.text}"/></td>
                        <td>
                            <input type="button" value="Изменить"
                            onclick="window.location.href='update?id=${accident.id}'"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>

</body>
</html>