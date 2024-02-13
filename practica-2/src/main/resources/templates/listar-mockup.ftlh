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

<div class="container mt-4">
    <a href="/crear-mockup" class="btn btn-primary mb-3">
       Nuevo Mockup
        <i class="bi bi-plus-lg"></i>
    </a>


    <h2>Listado de Mockups</h2>
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Nombre</th>
                <th>Proyecto</th>
                <th>Descripción</th>
                <th>Fecha Creación</th>
                <th>Fecha Expiración</th>
                <th>URL</th>
                <th>Acción</th>
            </tr>
            </thead>
            <tbody>
                <#list mockups as mockup>
                    <tr>
                        <td>${mockup.name}</td>
                        <td>${mockup.project.nombre}</td>
                        <td>${mockup.httpMethod} - ${mockup.contentType} - ${mockup.responseCode}</td>
                        <td>${mockup.createdDate?string["dd MMM yyyy, hh:mm:ss a "]}</td>
                        <td>${mockup.expirationDate?string["dd MMM yyyy, hh:mm:ss a "]}</td>
                        <td><a href="/${mockup.project.id}/${mockup.id}" class="link-underline link-underline-opacity-0 link-underline-opacity-75-hover">/${mockup.id}</a></td>
                        <td>
                            <a href="/modificar-mockup/${mockup.id}"><button class="btn btn-primary btn-sm">Modificar</button></a>
                            <a href="/eliminar-mockup/${mockup.id}"><button class="btn btn-danger btn-sm">Eliminar</button></a>
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