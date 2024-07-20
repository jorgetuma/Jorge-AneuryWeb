<#include "layout.ftl">
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
                    <a class="nav-link active" aria-current="page" href="/">Comprar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="carrito/${userId}">Carrito de compras</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/compras/listar/${userId}">Compras realizadas</a>
                </li>
                <li class="nav-item" >
                    <a class="nav-link" href="/admin/dashboard">Administrar</a>
                </li>
                <li class="nav-item" >
                    <a class="nav-link" href="/admin/users">Usuarios</a>
                </li>
            </ul>
            <a class="btn btn-outline-success" href="/logout">cerrar sesión</a>
        </div>
    </div>
</nav>

<div class="container mt-5 mb-5">
    <div class="d-flex justify-content-center row">
        <div class="col-md-8">
            <div class="p-2">
                <h3>Catálogo de libros</h3>
            </div>

            <div class="row my-3">
                <div class="input-group flex-nowrap">
                    <span class="input-group-text material-symbols-outlined" id="addon-wrapping">search</span>
                    <input type="text" class="form-control" placeholder="Buscar..." id="searchInput">
                    <button class="btn btn-outline-secondary" type="button" onclick="searchBy('titulo')">Por título</button>
                    <button class="btn btn-outline-secondary" type="button" onclick="searchBy('genero')">Por género</button>
                    <button class="btn btn-outline-secondary" type="button" onclick="searchBy('autor')">Por autor</button>
                </div>
<#--                <div class="col">-->
<#--                    <div class="input-group flex-nowrap">-->
<#--                        <span class="input-group-text">-->
<#--                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">-->
<#--                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />-->
<#--                            </svg>-->
<#--                        </span>-->
<#--                        <input type="text" class="form-control" id="searchInput" placeholder="buscar...">-->
<#--                        <button type="button" class="btn-sm btn-outline-secondary" onclick="searchBy('titulo')">por título</button>-->
<#--                        <button type="button" class="btn-sm btn-outline-secondary" onclick="searchBy('genero')">por género</button>-->
<#--                        <button type="button" class="btn-sm btn-outline-secondary" onclick="searchBy('autor')">por autor</button>-->
<#--                    </div>-->
<#--                </div>-->
            </div>

            <#list libros as l>
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="container text-center">
                            <div class="row align-items-center">
                                <div class="col justify-content-center">
                                    <p class="m-0"><a href="/review/ver/${l.id}">${l.titulo}</a></p>
                                    <p class="m-0 mt-1"><span class="text-secondary">Precio (RD): </span><span class="fw-bold">$${l.precio}</span></p>
                                    <p class="m-0"><span class="text-secondary">Autor: </span><span>${l.autor}</span></p>
                                    <p class="m-0"><span class="text-secondary">Género: </span><span>${l.genero}</span></p>
                                    <p class="m-0"><span class="text-secondary">Editorial: </span><span>${l.editorial}</span></p>
                                </div>
                                <div class="col">
                                    <input id="quantity-${l.id}" name="quantity-${l.id}" type="number" value="1" min="1" class="form-control w-50 mx-auto">
                                </div>
                                <div class="col">
<#--                                    <a href="/carrito/agregar/${l.id}&5bbcff04-31ab-4efc-9c2c-79ab1e3e1e21&1" class="btn btn-warning pay-button">Agregar al carrito</a>-->
                                    <input type="button" value="Agregar al carrito" class="btn btn-warning pay-button" onclick="addToCart('${l.id}')">
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
<#--                <form method="post" action="#">-->
<#--                    <div  class="d-flex flex-row justify-content-between align-items-center p-2 bg-white mt-4 px-3 rounded">-->
<#--                        <div class="product-details">-->
<#--                            <span class="font-weight-bold"><a href="/review/ver/${l.id}">${l.titulo}</a></span>-->
<#--                            <div class="size mr-5"><span class="text-grey">Precio (RD$):</span><span class="font-weight-bold">&nbsp;${l.precio}</span></div>-->
<#--                            <div class="size mr-5"><span class="text-grey">Autor:</span><span class="font-weight-bold">&nbsp;${l.autor}</span></div>-->
<#--                            <div class="size mr-5"><span class="text-grey">Género:</span><span class="font-weight-bold">&nbsp;${l.genero}</span></div>-->
<#--                            <div class="size mr-5"><span class="text-grey">Editorial:</span><span class="font-weight-bold">&nbsp;${l.editorial}</span></div>-->
<#--                        </div>-->
<#--                        <div class="d-flex flex-column align-items-center qty">-->
<#--                            <span class="font-weight-bold">Cantidad</span>-->
<#--                            <div class="d-flex flex-row product-desc">-->
<#--                                <div class="size mr-1">-->
<#--                                    <span class="text-grey">-->
<#--                                        <input id="quantity-id" name="quantity-id" type="number" value="1" min="1">-->
<#--                                    </span>-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                        <input type="submit" value="Agregar al carrito" class="btn btn-warning btn-block btn-lg ml-2 pay-button">-->
<#--                    </div>-->
<#--                </form>-->
            </#list>
        </div>
    </div>
</div>

<script>
    function searchBy(criteria) {

        let searchTerm = document.getElementById("searchInput").value;

        if (searchTerm.trim() !== '') {
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
    }
</script>

<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

<script>
    // function getTokenFromCookies() {
    //     const name = "Authorization=";
    //     const decodedCookie = decodeURIComponent(document.cookie);
    //     const ca = decodedCookie.split(';');
    //     for (let i = 0; i < ca.length; i++) {
    //         let c = ca[i];
    //         while (c.charAt(0) === ' ') {
    //             c = c.substring(1);
    //         }
    //         if (c.indexOf(name) === 0) {
    //             return c.substring(name.length, c.length);
    //         }
    //     }
    //     return "";
    // }

    function addToCart(bookId) {
        // console.log(bookId);
        const userId = ${userId};  // Replace with actual user ID retrieval logic
        // const token = getTokenFromCookies();
        const quantity = 1

        $.ajax({
            url: '/carrito/buscar-usuario/' + userId,
            type: 'POST',
            success: function(carrito) {
                // If cart exists, add the book to it
                addBookToCart(carrito.idCarrito, bookId, quantity);
            },
            error: function(e) {
                console.log(e);
                // If cart does not exist, create it first
                $.ajax({
                    url: '/carrito/crear/'+ userId,
                    type: 'POST',
                    // headers: {
                    //     'Authorization': 'Bearer ' + token
                    // },
                    success: function(carritoId) {
                        addBookToCart(carritoId, bookId, quantity);
                    },
                    error: function() {
                        alert("No se pudo agregar el libro al carrito, inténtalo de nuevo.");
                    }
                });
            }
        });
    }

    function addBookToCart(cartId, bookId, quantity) {
        // const token = getTokenFromCookies();
        $.ajax({
            url: '/carrito/agregar/'+bookId+'&'+cartId,
            type: 'POST',
            // headers: {
            //     'Authorization': 'Bearer ' + token
            // },
            data: {
                quantity: quantity
            },
            success: function() {
                alert("Libro agregado al carrito con éxito!");
            },
            error: function() {
                alert("No se pudo agregar el libro al carrito, inténtalo de nuevo.");
            }
        });
    }
</script>

</body>



