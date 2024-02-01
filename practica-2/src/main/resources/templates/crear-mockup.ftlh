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
<div class="container mt-5">
    <h2>Crea tu Mockup</h2>
    <form>
        <div class="form-group">
            <label for="endpointName">Nombre del Endpoint</label>
            <input type="text" class="form-control" id="endpointName" name="nombre" placeholder="Ingrese el nombre del Endpoint">
        </div>
        <div class="form-group">
            <label for="endpointRoute">Ruta del Endpoint</label>
            <input type="text" class="form-control" id="endpointRoute" name="ruta" placeholder="Ingrese la ruta del Endpoint">
        </div>
        <div class="form-group">
            <label for="accessMethod">Método de Acceso</label>
            <select class="form-control" id="accessMethod" name="metodo">
                <option>GET</option>
                <option>POST</option>
                <option>PUT</option>
                <option>PATCH</option>
                <option>DELETE</option>
                <option>OPTIONS</option>
            </select>
        </div>
        <div class="form-group">
            <label for="responseHeaders">Headers para la trama de respuesta</label>
            <input type="text" class="form-control" id="responseHeaders" name="headers" placeholder="Ingrese los headers separados por coma (key: value)">
        </div>
        <div class="form-group">
            <label for="responseCode">Código de respuesta de la petición</label>
            <select class="form-control" name="codigohttp" id="responseCode">
                <option>200 OK</option>
                <option>201 Created</option>
                <option>400 Bad Request</option>
                <option>401 Unauthorized</option>
                <option>403 Forbidden</option>
                <option>404 Not Found</option>
                <option>500 Internal Server Error</option>
            </select>
        </div>
        <div class="form-group">
            <label for="responseContentType">Content-Type para la trama de respuesta</label>
            <select class="form-control" id="responseContentType" name="content-type">
                <option>application/json</option>
                <option>text/html</option>
                <option>application/xml</option>
                <option>text/plain</option>
            </select>
        </div>
        <div class="form-group">
            <label for="responseBody">Cuerpo del mensaje en la respuesta</label>
            <textarea class="form-control" id="responseBody" rows="3" name="cuerpoRespuesta" placeholder="Ingrese el cuerpo del mensaje en la respuesta"></textarea>
        </div>
        <div class="form-group">
            <label for="expirationTime">Tiempo de expiración del Endpoint</label>
            <select class="form-control" id="expirationTime" name="expiracion">
                <option>1 mes</option>
                <option>1 semana</option>
                <option>1 día</option>
                <option>1 hora</option>
            </select>
        </div>
        <div class="form-group">
            <label for="responseDelay">Tiempo de demora de la respuesta (segundos)</label>
            <input type="number" class="form-control" id="responseDelay" placeholder="Ingrese el tiempo de demora de la respuesta">
        </div>
        <div class="form-check">
            <input type="checkbox" class="form-check-input" id="jwtValidation">
            <label class="form-check-label" for="jwtValidation">Habilitar validación vía JWT</label>
        </div>
        <button type="submit" class="btn btn-primary mt-3">Crear</button>
       <a href="/listar-mockup"><button type="button" class="btn btn-secondary mt-3">Cancelar</button></a>
    </form>
</div>
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
