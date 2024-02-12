<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Proyectos</title>
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
    <h1>Listado de Proyectos</h1>
    <hr>

    <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#crearProyectoModal">
        Crear Proyecto
    </button>

    <table class="table">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Usuario</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <#if size!=0>
            <#list proyectos as p>
                <tr>
                    <td>${p.nombre}</td>
                    <td>${p.descripcion}</td>
                    <td>${p.userName}</td>
                    <td>
                        <a href="/proyecto/modificar-proyecto/${p.id}"><button class="btn btn-primary btn-sm">Modificar</button></a>
                        <a href="/proyecto/eliminar-proyecto/${p.id}"><button class="btn btn-danger btn-sm">Eliminar</button></a>
                    </td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>

    <div>
        <#if pag <= cantpag>
            <a href="/proyecto/listar-proyecto/${pag + 1}" class="btn btn-primary">Siguiente</a>
        </#if>

        <#if pag!=1>
            <a href="/proyecto/listar-proyecto/${pag - 1}" class="btn btn-primary">Anterior</a>
        </#if>
    </div>

    <!-- Modal para crear un nuevo proyecto -->
    <div class="modal fade" id="crearProyectoModal" tabindex="-1" aria-labelledby="crearProyectoModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Crear Nuevo Proyecto</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="/proyecto/crear-proyecto" method="post" id="crearProyectoForm">
                        <div class="mb-3">
                            <label for="nombre">Nombre del Proyecto</label>
                            <input type="text" class="form-control" id="nombre" name="nombre">
                        </div>

                        <div class="mb-3">
                            <label for="descripcion">Descripción del Proyecto</label>
                            <input type="text" class="form-control" id="descripcion" name="descripcion">
                        </div>

                        <div class="mb-3">
                            <label for="username">Usuario Asociado</label>
                            <input type="text" class="form-control" id="username" name="username">
                        </div>

                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    <button type="submit" class="btn btn-primary" form="crearProyectoForm">Crear Proyecto</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>

