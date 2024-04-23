<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Catalogo de libros</title>
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
        .product-deta {
            margin-right: 70px;
        }
        .pay-button {
            color: #fff;
        }
        .pay-button:hover {
            color: #fff;
        }
        .pay-button:focus {
            color: #fff;
            box-shadow: none;
        }
        .text-grey {
            color: #a39f9f;
        }
        .qty{
            color: #377bff;
            font-size: 0.875em;
        }
        .product-details{
            width: 15em;
            text-align:center;
        }

        #quantity-id{
            width: 7em;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="/catalogo/listar">E-books</a>
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
                    <a class="nav-link" href="#">Compras realiazadas</a>
                </li>
                <li class="nav-iterm" >
                    <a class="nav-link" href="#">administrar</a>
                </li>
            </ul>
            <form class="d-flex" role="search">
                    <button class="btn btn-outline-success" type="submit" formaction="#"> login</button>
                <button class="btn btn-outline-success" type="submit" formaction="#">registrate</button>
                    <button class="btn btn-outline-success" type="submit" formaction="#">cerrar sesion</button>
            </form>
        </div>
    </div>
</nav>

<div class="container mt-5 mb-5">
    <div class="d-flex justify-content-center row">
        <div class="col-md-8">
            <div class="p-2">
                <h4>Catalogo de libros</h4>
            </div>

            <div class="row my-3">
                <div class="col">
                    <div class="input-group flex-nowrap">
                        <span class="input-group-text">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                            </svg>
                        </span>
                        <input type="text" class="form-control" id="searchInput" placeholder="buscar...">
                        <button type="button" class="btn-sm btn-outline-secondary" onclick="searchBy('titulo')">por título</button>
                        <button type="button" class="btn-sm btn-outline-secondary" onclick="searchBy('genero')">por género</button>
                        <button type="button" class="btn-sm btn-outline-secondary" onclick="searchBy('autor')">por autor</button>
                    </div>
                </div>
            </div>

            <#list libros as l>
                <form method="post" action="#">
                    <div  class="d-flex flex-row justify-content-between align-items-center p-2 bg-white mt-4 px-3 rounded">
                        <div class="product-details">
                            <span class="font-weight-bold"><a href="#">${l.titulo}</a></span>
                            <div class="size mr-5"><span class="text-grey">Precio (RD$):</span><span class="font-weight-bold">&nbsp;${l.precio}</span></div>
                            <div class="size mr-5"><span class="text-grey">Autor:</span><span class="font-weight-bold">&nbsp;${l.autor}</span></div>
                            <div class="size mr-5"><span class="text-grey">Género:</span><span class="font-weight-bold">&nbsp;${l.genero}</span></div>
                            <div class="size mr-5"><span class="text-grey">Editorial:</span><span class="font-weight-bold">&nbsp;${l.editorial}</span></div>
                        </div>
                        <div class="d-flex flex-column align-items-center qty">
                            <span class="font-weight-bold">Cantidad</span>
                            <div class="d-flex flex-row product-desc">
                                <div class="size mr-1">
                                    <span class="text-grey">
                                        <input id="quantity-id" name="quantity-id" type="number" value="1" min="1">
                                    </span>
                                </div>
                            </div>
                        </div>
                        <input type="submit" value="Agregar al carrito" class="btn btn-warning btn-block btn-lg ml-2 pay-button">
                    </div>
                </form>
            </#list>
        </div>
    </div>
</div>

<script>
    function searchBy(criteria) {

        let searchTerm = document.getElementById("searchInput").value;

        switch (criteria) {
            case 'titulo':
                window.location.href = "/catalogo/listar-titulo/" + searchTerm;
                break;
            case 'genero':
                window.location.href = "/catalogo/listar-genero/" + searchTerm;
                break;
            case 'autor':
                window.location.href = "/catalogo/listar-autor/" + searchTerm;
                break;
        }
    }
</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>



