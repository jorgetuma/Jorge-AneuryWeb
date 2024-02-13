<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Listado de Usuarios</title>
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
                    <a class="nav-link" aria-current="page" href="/">Mockups</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/proyecto/listar-proyecto/1">Proyectos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/usuario/listar-usuario/1">Usuarios</a>
                </li>
            </ul>
            <span class="navbar-text">
                <a href="/logout" class="nav-link">Logout</a>
            </span>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="mb-3">Listado de Usuarios</h2>
    <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#nuevoUsuarioModal">
        Nuevo Usuario
        <i class="bi bi-plus-lg"></i>
    </button>

    <table class="table">
        <thead>
        <tr>
            <th>Username</th>
            <th>Nombre</th>
            <th>Rol</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <#if size!=0>
            <#list usuarios as u>
                <tr>
                    <td>${u.userName}</td>
                    <td>${u.nombre}</td>
                    <td>${u.roles[0].role}</td>
                    <td>
                        <a href="/usuario/modificar-usuario/${u.userName}"><button class="btn btn-primary btn-sm">Modificar</button></a>
                        <a href="/usuario/eliminar-usuario/${u.userName}"><button class="btn btn-danger btn-sm">Eliminar</button></a>
                    </td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>

    <div>
        <#if pag <= cantpag>
            <a href="/usuario/listar-usuario/${pag + 1}" class="btn btn-primary">Siguiente</a>
        </#if>

        <#if pag!=1>
            <a href="/usuario/listar-usuario/${pag - 1}" class="btn btn-primary">Anterior</a>
        </#if>
    </div>

</div>

<!-- Modal Nuevo Usuario -->
<div class="modal fade" id="nuevoUsuarioModal" tabindex="-1" aria-labelledby="nuevoUsuarioModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="nuevoUsuarioModalLabel">Agregar Nuevo Usuario</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="/usuario/agregar-usuario" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </#if>
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre">
                    </div>
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="username" name="username">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <div class="mb-3">
                        <label for="rol" class="form-label">Rol</label>
                        <select class="form-select" id="rol" name="rol">
                            <option value="ROLE_USER">ROLE_USER</option>
                            <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                        </select>
                    </div>
                        <button type="submit" class="btn btn-primary">Crear Usuario</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>

