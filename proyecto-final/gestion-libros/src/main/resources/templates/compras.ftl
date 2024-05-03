<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compras Realizadas</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
                    <a class="nav-link" href="/compras/listar/userid">Compras realiazadas</a>
                </li>
                <li class="nav-iterm" >
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

<div class="container mt-5">
    <div class="row">
        <div class="col-md-12">
            <h2>Compras Realizadas</h2>


            <div class="input-group flex-nowrap">
                        <span class="input-group-text">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                            </svg>
                        </span>
                <input type="text" class="form-control" id="searchInput" placeholder="Escribe para buscar..." onkeyup="filterTable()">
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th>ID trasacción</th>
                    <th>Cantidad articulos</th>
                    <th>Fecha</th>
                    <th>Monto(RD$)</th>
                    <th>Estatus de Pago</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <#list compras as c>
                    <td>${c.transaccion}</td>
                    <td>${c.libros.size()}</td>
                    <td>${c.fecha}</td>
                    <td>${c.montoCompra}</td>
                    <td>${c.estatusPago}</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>

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

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
