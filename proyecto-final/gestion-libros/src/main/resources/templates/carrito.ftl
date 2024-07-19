<#include "layout.ftl">
<body>
<#--<style>*{margin: 0;padding: 0;-webkit-font-smoothing: antialiased;-webkit-text-shadow: rgba(0,0,0,.01) 0 0 1px;text-shadow: rgba(0,0,0,.01) 0 0 1px}body{font-family: 'Rubik', sans-serif;font-size: 14px;font-weight: 400;background: #E0E0E0;color: #000000}ul{list-style: none;margin-bottom: 0px}.button{display: inline-block;background: #0e8ce4;border-radius: 5px;height: 48px;-webkit-transition: all 200ms ease;-moz-transition: all 200ms ease;-ms-transition: all 200ms ease;-o-transition: all 200ms ease;transition: all 200ms ease}.button a{display: block;font-size: 18px;font-weight: 400;line-height: 48px;color: #FFFFFF;padding-left: 35px;padding-right: 35px}.button:hover{opacity: 0.8}.cart_section{width: 100%;padding-top: 93px;padding-bottom: 111px}.cart_title{font-size: 30px;font-weight: 500}.cart_items{margin-top: 8px}.cart_list{border: solid 1px #e8e8e8;box-shadow: 0px 1px 5px rgba(0,0,0,0.1);background-color: #fff}.cart_item{width: 100%;padding: 15px;padding-right: 46px}.cart_item_image{width: 133px;height: 133px;float: left}.cart_item_image img{max-width: 100%}.cart_item_info{width: calc(100% - 133px);float: left;padding-top: 18px}.cart_item_name{margin-left: 7.53%}.cart_item_title{font-size: 14px;font-weight: 400;color: rgba(0,0,0,0.5)}.cart_item_text{font-size: 18px;margin-top: 35px}.cart_item_text span{display: inline-block;width: 20px;height: 20px;border-radius: 50%;margin-right: 11px;-webkit-transform: translateY(4px);-moz-transform: translateY(4px);-ms-transform: translateY(4px);-o-transform: translateY(4px);transform: translateY(4px)}.cart_item_price{text-align: right}.cart_item_total{text-align: right}.order_total{width: 100%;height: 60px;margin-top: 30px;border: solid 1px #e8e8e8;box-shadow: 0px 1px 5px rgba(0,0,0,0.1);padding-right: 46px;padding-left: 15px;background-color: #fff}.order_total_title{display: inline-block;font-size: 14px;color: rgba(0,0,0,0.5);line-height: 60px}.order_total_amount{display: inline-block;font-size: 18px;font-weight: 500;margin-left: 26px;line-height: 60px}.cart_buttons{margin-top: 60px;text-align: right}.cart_button_clear{display: inline-block;border: none;font-size: 18px;font-weight: 400;line-height: 48px;color: rgba(0,0,0,0.5);background: #FFFFFF;border: solid 1px #b2b2b2;padding-left: 35px;padding-right: 35px;outline: none;cursor: pointer;margin-right: 26px}.cart_button_clear:hover{border-color: #0e8ce4;color: #0e8ce4}.cart_button_checkout{display: inline-block;border: none;font-size: 18px;font-weight: 400;line-height: 48px;color: #FFFFFF;padding-left: 35px;padding-right: 35px;outline: none;cursor: pointer;vertical-align: top}</style>-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">G5books</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/">Comprar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="#">Carrito de compras</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/compras/listar/userid">Compras realizadas</a>
                </li>
                <li class="nav-iterm" >
                    <a class="nav-link" href="/admin/dashboard">Administrar</a>
                </li>
            </ul>
            <a class="btn btn-outline-success" href="/logout">cerrar sesión</a>
        </div>
    </div>
</nav>

<div class="container mt-5 mb-5">
    <h3 class="mb-3">Carrito de compras <span class="badge text-bg-success">${cantItems} items</span></h3>

    <#list items as item>
        <div class="card mb-3 p-3">
            <div class="card-body">
                <div class="container text-center">
                    <div class="row align-items-center">
                        <div class="col justify-content-center">
                            <h6>Nombre</h6>
                            ${item.libro.titulo}
                        </div>
                        <div class="col justify-content-center">
                            <h6>Cantidad</h6>
                            ${item.cantidad}
                        </div>
                        <div class="col justify-content-center">
                            <h6>Precio(RD$)</h6>
                            $${item.libro.precio}
                        </div>
                        <div class="col justify-content-center">
                            <h6>Total(RD$)</h6>
                            $${item.calcularTotal()}
                        </div>
                        <div class="col justify-content-center">
                                <a class="btn btn-outline-danger" href="/carrito/quitar/${item.libro.id}&${carrito.idCarrito}&${user}">Quitar del carrito</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </#list>

    <div class="d-flex justify-content-between">
        <div>
            <h6>Total orden(RD$)</h6>
            <u>$${totalCarrito}</u>
        </div>

        <#if totalCarrito gt 0>
        <div>
            <a class="btn btn-outline-danger" href="/carrito/limpiar/${carrito.idCarrito}&${user}">Limpiar carrito</a>
            <button class="btn btn-outline-success" type="submit" form="paypalForm">Procesar compra</button>
<#--            <a class="btn btn-outline-success" href="/crud/procesar-compra">Procesar compra</a>-->

            <form role="form" action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post" class="d-none" id="paypalForm">

                <input type="hidden" name="cmd" value="_xclick">
                <input type="hidden" name="business" value="sb-4ts43r31794824@business.example.com" >
                <input type="hidden" name="currency_code" value="USD">

                <!-- Para uso general, tienen 256 caractares para utilizar -->
                <input type="hidden" name="custom" value="${token}">

                <input type="hidden" name="cbt" value="Completar proceso de Compra"> <!-- Información para mostrar-->
                <input type="hidden" name="rm" value="2"> <!-- Indicando que haga un redirect por el metodo POST -->
                <input type="hidden" name="return" value="http://localhost:8181/carrito/procesarCompraPaypal">
<#--                <input type="hidden" name="return" value="http://localhost:8181/carrito/procesarCompraPaypal">-->
                <input type="hidden" name="cancel_return" value="http://localhost:8181/">

                <input type="hidden" class="form-control" id="invoice" name="invoice" value="${carrito.idCarrito}" required="required">
                <input type="hidden" class="form-control" id="item_name" name="item_name" value="Compra ${carrito.idCarrito}" required="required">
                <input type="hidden" class="form-control" id="amount" name="amount" value="${totalCarrito}" required="required">
<#--                <button type="submit" class="btn btn-primary">Comprar vía Paypal</button>-->
            </form>
        </div>
        </#if>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
</body>