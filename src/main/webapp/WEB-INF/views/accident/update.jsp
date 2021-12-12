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
    <title>Update accident</title>
</head>
<body>

<div class="container pt-1">
    <div class="row">
        <div class="card text-secondary" style="width: 100%">
            <div class="card-header" style="font-weight: bold; font-size: larger; text-align: center">
                Форма для редактирования записи об инциденте
            </div>
            <div class="card-body">
                <form action="save?id=${accident.id}" method='POST'>
                    <div class="form-group row">
                        <label class="col-form-label col-sm-3" for="name" style="font-weight: 900">Название
                            инцидента</label>
                        <div class="col-sm-5">
                            <input type='text' class="form-control" name='name' id="name" value="${accident.name}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-form-label col-sm-3" for="address" style="font-weight: 900">Адрес
                            инцидента</label>
                        <div class="col-sm-5">
                            <input type='text' class="form-control" name='address' id="address" value="${accident.address}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-form-label col-sm-3" for="text" style="font-weight: 900">Описание
                            инцидента</label>
                        <div class="col-sm-5">
                            <input type='text' class="form-control" name='text' id="text" value="${accident.text}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="label" style="font-weight: 900"></label>
                        <div class="col-sm-5" style="text-align: left">
                            <button type="submit" class="btn btn-success">Сохранить</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
