<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Listado de Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <button class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#nuevoUsuarioModal">Nuevo Usuario</button>

    <h2>Listado de Usuarios</h2>
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
                        <input type="text" class="form-control" id="password" name="password">
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

