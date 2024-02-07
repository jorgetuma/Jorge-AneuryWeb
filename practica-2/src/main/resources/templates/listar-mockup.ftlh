<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Listado de Mockups</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
   <a href="/crear-mockup"><button class="btn btn-success mb-3" > Nuevo Mockup</button></a>

    <h2>Listado de Mockups</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Fecha</th>
            <th>URL</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody>
            <#list mockups as mockup>
                <tr>
                    <td>${mockup.name}</td>
                    <td>${mockup.description}</td>
                    <td>${mockup.expirationDate}</td>
                    <td><a href="/${mockup.id}" class="link-underline link-underline-opacity-0 link-underline-opacity-75-hover">/${mockup.id}</a></td>
                    <td>
                        <a href="#"><button class="btn btn-primary btn-sm">Modificar</button></a>
                        <a href="#"><button class="btn btn-danger btn-sm">Eliminar</button></a>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>