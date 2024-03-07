<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Listado de Mockups</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">MockAPI</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/">Mockups</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/proyecto/listar-proyecto/1">Proyectos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/usuario/listar-usuario/1">Usuarios</a>
                </li>
            </ul>
            <span class="navbar-text">
                <a href="/logout" class="nav-link">Logout</a>
            </span>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="mb-3">Listado de Mockups</h2>
    <a href="/crear-mockup" class="btn btn-primary mb-3">
        Nuevo Mockup
        <i class="bi bi-plus-lg"></i>
    </a>

    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Nombre</th>
                <th>Proyecto</th>
                <th>Descripción</th>
                <th>Fecha Creación</th>
                <th>Fecha Expiración</th>
                <th>Acción</th>
            </tr>
            </thead>
            <tbody>
            <#list mockups as mockup>
                <tr>
                    <td><a href="/mockup-details/${mockup.id}" class="link-underline link-underline-opacity-0 link-underline-opacity-75-hover">${mockup.name}</a></td>
                    <td>${mockup.project.nombre}</td>
                    <td>${mockup.httpMethod} - ${mockup.contentType} - ${mockup.responseCode}</td>
                    <td>${mockup.createdDate?string["dd MMM yyyy, hh:mm:ss a "]}</td>
                    <td>${mockup.expirationDate?string["dd MMM yyyy, hh:mm:ss a "]}</td>
                    <td>
                        <button class="btn btn-success btn-sm" id="${mockup_index}">Probar</button>
                        <#--                            <a class="btn btn-success btn-sm" href="/test/${mockup.id}">Probar</a>-->
                        <a href="/modificar-mockup/${mockup.id}" class="btn btn-primary btn-sm">Modificar</a>
                        <a href="/eliminar-mockup/${mockup.id}" class="btn btn-danger btn-sm">Eliminar</a>
                        <script>
                            document.getElementById("${mockup_index}").addEventListener("click", function() {
                                var xhr = new XMLHttpRequest();

                                xhr.onload = function() {
                                    window.history.pushState({}, '', xhr.responseURL);
                                    document.body.innerHTML = xhr.response;
                                };

                                var url = '/${mockup.project.id}/${mockup.id}';
                                xhr.open('${mockup.httpMethod}', url);

                                <#if mockup.jwtToken?? && mockup.jwtEnabled>
                                xhr.setRequestHeader('Authorization', 'Bearer ${mockup.jwtToken}');
                                </#if>

                                xhr.send();
                            });
                        </script>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>