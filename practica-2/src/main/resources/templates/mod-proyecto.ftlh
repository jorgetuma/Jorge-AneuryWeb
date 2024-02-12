<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Proyecto</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">MockAPI</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/">Mockups</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/proyecto/listar-proyecto/1">Proyectos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/usuario/listar-usuario/1">Usuarios</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2>Modificar Proyecto</h2>
    <form action="/proyecto/mod-proyecto/${proyecto.id}" method="post">
        <#if _csrf??>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </#if>
        <div class="form-group">
            <label for="username">Usuario:</label>
            <input type="text" class="form-control" id="username" name="username" value="${proyecto.userName}" readonly>
        </div>
        <div class="form-group">
            <label for="nombre">Nombre del Proyecto:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre del Proyecto" value="${proyecto.nombre}">
        </div>
        <div class="form-group">
            <label for="descripcion">Descripción:</label>
            <input class="form-control" id="descripcion" name="descripcion"  placeholder="Descripción del Proyecto" value="${proyecto.descripcion}">
        </div>
        <button type="submit" class="btn btn-primary">Modificar</button>
       <a href="/proyecto/listar-proyecto/1"><button type="button" class="btn btn-secondary">cancelar</button></a>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
