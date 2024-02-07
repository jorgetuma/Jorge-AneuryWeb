<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crea tu Mockup</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-5">
    <h2>Crea tu Mockup</h2>
    <form action="crear-mockup" method="POST">
        <div class="form-group">
            <label for="name">Nombre del Endpoint</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Ingrese el nombre del Endpoint">
        </div>

        <div class="form-group">
            <label for="description">Descripción</label>
            <textarea class="form-control" id="description" rows="3" name="description" placeholder="Ingrese la descripción"></textarea>
        </div>

        <div class="form-group">
            <label for="httpMethod">Método de Acceso</label>
            <select class="form-control" id="httpMethod" name="httpMethod" required>
                <option value="GET">GET</option>
                <option value="POST">POST</option>
                <option value="PUT">PUT</option>
                <option value="PATCH">PATCH</option>
                <option value="DELETE">DELETE</option>
                <option value="OPTIONS">OPTIONS</option>
            </select>
        </div>

        <div class="form-group">
            <label for="headers">Headers para la trama de respuesta</label>
            <input type="text" class="form-control" id="responseHeaders" name="headers" placeholder="Ingrese los headers separados por coma (key: value)">
        </div>

        <div class="form-group">
            <label for="responseCode">Código de respuesta de la petición</label>
            <select class="form-control" name="responseCode" id="responseCode">
                <option value="200">200 OK</option>
                <option value="201">201 Created</option>
                <option value="400">400 Bad Request</option>
                <option value="401">401 Unauthorized</option>
                <option value="403">403 Forbidden</option>
                <option value="404">404 Not Found</option>
                <option value="500">500 Internal Server Error</option>
            </select>
        </div>

        <div class="form-group">
            <label for="contentType">Content-Type para la trama de respuesta</label>
            <select class="form-control" id="contentType" name="contentType" required>
                <option>application/json</option>
                <option>text/html</option>
                <option>application/xml</option>
                <option>text/plain</option>
            </select>
        </div>

        <div class="form-group">
            <label for="responseBody">Cuerpo del mensaje en la respuesta</label>
            <textarea class="form-control" id="responseBody" rows="3" name="responseBody" placeholder="Ingrese el cuerpo del mensaje en la respuesta"></textarea>
        </div>

        <div class="form-group">
            <label for="expirationDate">Tiempo de expiración del Endpoint</label>
            <select class="form-control" id="expirationDate" name="expirationDate">
                <option value="1 anio" selected>1 año</option>
                <option value="1 mes">1 mes</option>
                <option value="1 semana">1 semana</option>
                <option value="1 dia">1 día</option>
                <option value="1 hora">1 hora</option>
            </select>
        </div>

        <div class="form-group">
            <label for="responseDelay">Tiempo de demora de la respuesta (segundos)</label>
            <input type="number" name="responseDelay" class="form-control" id="responseDelay" placeholder="Ingrese el tiempo de demora de la respuesta" min="0.00" value="0.00" step="0.01">
        </div>

        <div class="form-check">
            <input type="checkbox" class="form-check-input" id="jwtEnabled" name="jwtEnabled">
            <label class="form-check-label" for="jwtEnabled">Habilitar validación vía JWT</label>
        </div>

        <button type="submit" class="btn btn-primary mt-3">Crear</button>
        <a href="/listar-mockup" class="btn btn-secondary mt-3">Cancelar</a>
    </form>
</div>
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
