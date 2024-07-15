<#include "layout.ftl">

<head>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <style>
        body{
            background-color: #edf1f5;
            margin-top:20px;
        }
        .card {
            margin-bottom: 30px;
        }
        .card {
            position: relative;
            display: flex;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 0 solid transparent;
            border-radius: 0;
        }
        .card .card-subtitle {
            font-weight: 300;
            margin-bottom: 10px;
            color: #8898aa;
        }
        .table-product.table-striped tbody tr:nth-of-type(odd) {
            background-color: #f3f8fa!important
        }
        .table-product td{
            border-top: 0px solid #dee2e6 !important;
            color: #728299!important;
        }
        .button{display: inline-block;background: rgb(14, 228, 117);border-radius: 5px;height: 48px;-webkit-transition: all 200ms ease;-moz-transition: all 200ms ease;-ms-transition: all 200ms ease;-o-transition: all 200ms ease;transition: all 200ms ease}
    </style>

</head>

<body>

<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">G5books</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/">comprar</a>
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
                <button class="btn btn-outline-success" type="submit" formaction="#"> login</button>
                <button class="btn btn-outline-success" type="submit" formaction="#">registrate</button>
                <button class="btn btn-outline-success" type="submit" formaction="#">cerrar sesion</button>
            </form>
        </div>
    </div>
</nav>

<div class="container">
    <div class="card">
        <div class="card-body">
            <h3 class="card-title">${libro.titulo}</h3>
            <div class="row">

                <div class="col-lg-7 col-md-7 col-sm-6">
                    <h4 class="box-title mt-5">Autor</h4>
                    <p>${libro.autor}</p>
                    <h4 class="box-title mt-5">Genero</h4>
                    <p>${libro.genero}</p>
                    <h4 class="box-title mt-5">Editorial</h4>
                    <p>${libro.editorial}</p>
                    <h2 class="mt-5">
                        ${libro.precio} RD$
                    </h2>
                    <button class="button btn-light"><a href="#">agregar al carrito</a></button>
                </div>
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <h3 class="box-title mt-5">Reseñas</h3>
                    <div class="table-responsive">
                        <table class="table table-striped table-product">
                            <tbody>
                            <#list reviews as r>
                                <tr>
                                    <td width="390">${r.idUsuario}</td>
                                    <td>${r.review}</td>
                                    <td>${r.calificacion} de 10</td>
                                        <td><button class="button btn-light"><a href="/review/editar/${r.idReview}&${libro.id}">editar</a></button></td>
                                        <td><button class="button btn-light"><a href="/review/eliminar/${r.idReview}&${libro.id}">eliminar</a></button></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#reviewModal">
                            Agregar reseña
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal" id="reviewModal" tabindex="-1" aria-labelledby="reviewModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="reviewModalLabel">Agregar Reseña</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="reviewForm" method="post" action="/review/crear/username&${libro.id}">
                    <div class="form-group">
                        <label for="review">Reseña:</label>
                        <textarea name="review" class="form-control" id="review" rows="3"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="calificacion">Calificación (0-10):</label>
                        <input name="calificacion" type="number" class="form-control" id="calificacion" min="0" max="10">
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
</body>