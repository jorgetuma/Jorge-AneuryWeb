<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Listado de Dispositivos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Listado de Dispositivos</h2>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Status</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <#if size!=0>
            <#list dispositivos as d>
                <tr>
                    <td>${d.idDispositivo}</td>
                    <td>Activo</td>
                    <td><a href="/dashboard/${d.idDispositivo}"><button class="btn btn-primary btn-sm">Ir al Dashboard</button></a></td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>