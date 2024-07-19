<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vista Previa de Factura</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }
        .invoice-container {
            max-width: 800px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
        }
        .header, .footer {
            text-align: center;
            margin-bottom: 20px;
        }
        .details {
            margin-bottom: 20px;
        }
        .details .item {
            margin-bottom: 10px;
        }
        .items {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        .items th, .items td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }
        .items th {
            background-color: #f2f2f2;
        }
        .actions {
            text-align: center;
        }
        .actions button {
            margin: 5px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
        }
        .cancel-button {
            background-color: #ff4d4d;
            color: white;
        }
        .proceed-button {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<div class="invoice-container">
    <div class="header">
        <h1>Factura</h1>
        <p>ID del Pedido: <strong>@Id</strong></p>
    </div>
    <div class="details">
        <div class="item"><strong>Nombre:</strong> <span>${pedido.nombre}</span></div>
        <div class="item"><strong>ID Usuario:</strong> <span>${pedido.idUser}</span></div>
        <div class="item"><strong>Fecha:</strong> <span>${pedido.fecha}</span></div>
        <div class="item"><strong>ID del Comprador:</strong> <span>${pedido.compradorId}</span></div>
        <div class="item"><strong>Email del Comprador:</strong> <span>${pedido.emailComprador}</span></div>
        <div class="item"><strong>Vendedor:</strong> <span>${pedido.vendedor}</span></div>
        <div class="item"><strong>Dirección:</strong> <span>${pedido.direccion}</span></div>
        <div class="item"><strong>Ciudad:</strong> <span>${pedido.ciudad}</span></div>
        <div class="item"><strong>Estado:</strong> <span>${pedido.estado}</span></div>
        <div class="item"><strong>ZIP:</strong> <span>${pedido.zip}</span></div>
        <div class="item"><strong>Estatus del Pago:</strong> <span>${pedido.estatusPago}</span></div>
        <div class="item"><strong>Transacción:</strong> <span>${pedido.transaccion}</span></div>
    </div>
    <div class="details">
        <div class="item"><strong>Monto de Compra:</strong> <span>${pedido.montoCompra}</span></div>
        <div class="item"><strong>Monto de Fee:</strong> <span>${pedido.montoFee}</span></div>
        <div class="item"><strong>Monto de Envío:</strong> <span>${pedido.montoEnvio}</span></div>
        <div class="item"><strong>Monto de Manejo:</strong> <span>${pedido.montoManejo}</span></div>
        <div class="item"><strong>Factura:</strong> <span>${pedido.factura}</span></div>
    </div>
    <div class="actions">
        <a href="/catalogo/listar"><button class="proceed-button">Proceder</button>
    </div>
    <div class="footer">
        <p>Gracias por su compra!</p>
    </div>
</div>
</body>
</html>
