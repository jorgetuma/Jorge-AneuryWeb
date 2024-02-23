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

    <div class="card">
        <h5 class="card-header">Detalles Mockup</h5>
        <div class="card-body">
            <h5 class="card-title mb-3">${mockup.name}</h5>
            <div class="row">
                <div class="col-6 mb-2">
                    <h6>Proyecto:</h6>
                    <p>${mockup.project.nombre}</p>
                </div>
                <div class="col-6 mb-2">
                    <h6>Fecha Expiración:</h6>
                    <p>${mockup.expirationDate?string["dd MMM yyyy, hh:mm:ss a "]}</p>
                </div>
                <div class="col-6 mb-2">
                    <h6>Método:</h6>
                    <p>${mockup.httpMethod}</p>
                </div>
                <div class="col-6 mb-2">
                    <h6>Código Respuesta:</h6>
                    <p>${mockup.responseCode}</p>
                </div>
                <div class="col-12 mb-2">
                    <h6>URL:</h6>
                    <p>/${mockup.id}</p>
                </div>
                <#if mockup.jwtToken??>
                    <div class="col-12 mb-2">
                        <h6>JWT Token:</h6>
                        <p>Bearer ${mockup.jwtToken}</p>
                    </div>
                </#if>
            </div>
            <a href="/" class="btn btn-primary">Regresar a Inicio</a>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>