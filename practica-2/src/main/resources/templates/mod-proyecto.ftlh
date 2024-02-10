<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Proyecto</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Modificar Proyecto</h2>
    <form action="/proyecto/mod-proyecto/${proyecto.id}" method="post">
        <div class="form-group">
            <label for="username">Usuario:</label>
            <input type="text" class="form-control" id="username" name="username" value="${proyecto.userName}" readonly>
        </div>
        <div class="form-group">
            <label for="nombre">Nombre del Proyecto:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre del Proyecto" value="${proyecto.nombre}">
        </div>
        <div class="form-group">
            <label for="descripcion">Descripción:</label>
            <input class="form-control" id="descripcion" name="descripcion"  placeholder="Descripción del Proyecto" value="${proyecto.descripcion}">
        </div>
        <button type="submit" class="btn btn-primary">Modificar</button>
       <a href="/proyecto/listar-proyecto/1"><button type="button" class="btn btn-secondary">cancelar</button></a>
    </form>
</div>

<!-- Bootstrap JS (Optional) -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
</body>
</html>
