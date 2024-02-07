<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Modificar Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <h2>Modificar Usuario</h2>
    <form id="formModificarUsuario" action="/usuario/mod-usuario" method="post">
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="username" name="username" value="${usuario.userName}" readonly>
        </div>
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="${usuario.nombre}">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Contraseña</label>
            <input type="text" class="form-control" id="password" name="password" value="${usuario.passWord}">
        </div>
        <#if usuario.roles[0].role == "ROLE_USER">
            <div class="mb-3">
                <label for="rol" class="form-label">Rol</label>
                <select class="form-select" id="rol" name="rol">
                    <option SELECTED value="ROLE_USER">ROLE_USER</option>
                    <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                </select>
            </div>
        </#if>

        <#if usuario.roles[0].role == "ROLE_ADMIN">
            <div class="mb-3">
                <label for="rol" class="form-label">Rol</label>
                <select class="form-select" id="rol" name="rol">
                    <option value="ROLE_USER">ROLE_USER</option>
                    <option SELECTED value="ROLE_ADMIN">ROLE_ADMIN</option>
                </select>
            </div>
        </#if>
        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        <a href="/usuario/listar-usuario/1"> <button type="button" class="btn btn-secondary">Cancelar</button></a>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
