<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Usuarios y Perfiles</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Manrope:wght@200&display=swap');
        body {
            font-family: 'Manrope', sans-serif;
            background:#eee;
        }
        .size span {
            font-size: 11px;
        }
        .color span {
            font-size: 11px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="/catalogo/listar">G5books</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/catalogo/listar">comprar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">carrito de compras</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/compras/listar/userid">Compras realizadas</a>
                </li>
                <li class="nav-iterm">
                    <a class="nav-link" href="/admin/dashboard">administrar</a>
                </li>
            </ul>
            <form class="d-flex" role="search">
                <button class="btn btn-outline-success" type="submit" formaction="#">login</button>
                <button class="btn btn-outline-success" type="submit" formaction="#">registrate</button>
                <button class="btn btn-outline-success" type="submit" formaction="#">cerrar sesion</button>
            </form>
        </div>
    </div>
</nav>
<h1>Usuarios y Perfiles</h1>

<div class="input-group flex-nowrap">
    <span class="input-group-text">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
        </svg>
    </span>
    <input type="text" class="form-control" id="searchInput" placeholder="Escribe para buscar..." onkeyup="filterTable()">
</div>

<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th>Nombre</th>
        <th>Email</th>
        <th>Rol</th>
        <th>Accion</th>
    </tr>
    </thead>
    <tbody id="table">
    <!-- Aquí va el bucle para listar los usuarios -->
    <#list usuarios as u>
        <tr>
            <td>${u.nombre}</td>
            <td>${u.email}</td>
            <td>${u.rol}</td>
            <td>
                <button class="btn-sm btn-primary" data-toggle="modal" data-target="#modifyUserModal">Modificar</button>
                <button class="btn-sm btn-danger">Eliminar</button>
            </td>
        </tr>
    </#list>
    </tbody>
</table>

<div class="container-center">
    <button class="btn-lg btn-primary" data-toggle="modal" data-target="#createUserModal">Crear usuario</button>
</div>

<!-- Modal para crear usuario -->
<div class="modal fade" id="createUserModal" tabindex="-1" role="dialog" aria-labelledby="createUserModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="createUserModalLabel">Crear Usuario</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="createUserForm">
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" id="nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña</label>
                        <input type="password" class="form-control" id="password" required>
                    </div>
                    <div class="form-group">
                        <label for="rol">Rol</label>
                        <select class="form-control" id="rol" required>
                            <option>Administrador</option>
                            <option>Cliente</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Crear</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal para modificar usuario -->
<div class="modal fade" id="modifyUserModal" tabindex="-1" role="dialog" aria-labelledby="modifyUserModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modifyUserModalLabel">Modificar Usuario</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="modifyUserForm">
                    <div class="form-group">
                        <label for="modifyNombre">Nombre</label>
                        <input type="text" class="form-control" id="modifyNombre" required>
                    </div>
                    <div class="form-group">
                        <label for="modifyEmail">Email</label>
                        <input type="email" class="form-control" id="modifyEmail" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña</label>
                        <input type="password" class="form-control" id="password" required>
                    </div>
                    <div class="form-group">
                        <label for="modifyRol">Rol</label>
                        <select class="form-control" id="modifyRol" required>
                            <option>Administrador</option>
                            <option>Cliente</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Modificar</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script>
    function filterTable() {
        let input, filter, table, tr, td, i, j, visible;
        input = document.getElementById("searchInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("table");
        tr = table.getElementsByTagName("tr");

        for (i = 0; i < tr.length; i++) {
            visible = false;
            if (tr[i].getElementsByTagName("th").length == 0) {
                td = tr[i].getElementsByTagName("td");
                for (j = 0; j < td.length; j++) {
                    if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
                        visible = true;
                    }
                }
            } else {
                visible = true;
            }
            if (visible) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
</script>
</body>
</html>