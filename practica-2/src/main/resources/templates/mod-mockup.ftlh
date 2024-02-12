<#import "spring.ftl" as spring>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crea tu Mockup</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">MockAPI</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/">Mockups</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/proyecto/listar-proyecto/1">Proyectos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/usuario/listar-usuario/1">Usuarios</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container my-3">
    <h2 class="mb-3">Modifica tu Mockup</h2>
    <form action="/mod-mockup/${mockup.id}" method="POST">
        <div class="mb-3">
            <label for="name" class="form-label">Nombre del Endpoint</label>
            <input type="text" class="form-control" id="name" name="name" value="${mockup.name}" placeholder="Ingrese el nombre del Endpoint">
        </div>

        <div class="mb-3">
            <label for="project" class="form-label">Proyecto</label>
            <select id="project" class="form-select" name="project" required>
                <option value="${mockup.project.id}">${mockup.project.nombre}</option>
                <#list projects as project>
                    <option value="${project.id}">${project.nombre}</option>
                </#list>
            </select>
        </div>


        <div class="mb-3">
            <label for="httpMethod" class="form-label">Método de Acceso</label>
            <select class="form-control" id="httpMethod" name="httpMethod" required>
                <option value="${mockup.httpMethod}">${mockup.httpMethod}</option>
                <option value="GET">GET</option>
                <option value="POST">POST</option>
                <option value="PUT">PUT</option>
                <option value="PATCH">PATCH</option>
                <option value="DELETE">DELETE</option>
                <option value="OPTIONS">OPTIONS</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="headers" class="form-label">Headers para la trama de respuesta</label>
            <textarea class="form-control" id="responseHeaders" name="headers" placeholder="Ingrese los headers como JSON" rows="3"></textarea>
            <#if errorHeaders??>
                <p class="text-danger">${errorHeaders}</p>
            </#if>
        </div>

        <div class="mb-3">
            <label for="responseCode" class="form-label">Código de respuesta de la petición</label>
            <select class="form-control" name="responseCode" id="responseCode">
                <option value="${mockup.responseCode}">${mockup.responseCode}</option>
                <option value="200">200 OK</option>
                <option value="201">201 Created</option>
                <option value="400">400 Bad Request</option>
                <option value="401">401 Unauthorized</option>
                <option value="403">403 Forbidden</option>
                <option value="404">404 Not Found</option>
                <option value="500">500 Internal Server Error</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="contentType" class="form-label">Content-Type para la trama de respuesta</label>
            <select class="form-control" id="contentType" name="contentType" required>
                <option value="${mockup.contentType}">${mockup.contentType}</option>
                <option value="application/json">application/json</option>
                <option value="application/xml">application/xml</option>
                <option value="text/html">text/html</option>
                <option value="text/plain">text/plain</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="responseBody" class="form-label">Cuerpo del mensaje en la respuesta</label>
            <textarea class="form-control" id="responseBody" rows="3" name="responseBody" placeholder="Ingrese el cuerpo del mensaje en la respuesta">${mockup.responseBody}</textarea>
            <#if errorResponseBody??>
                <p class="text-danger">${errorResponseBody}</p>
            </#if>
        </div>

        <div class="mb-3">
            <label for="expirationDate" class="form-label">Tiempo de expiración del Endpoint</label>
            <select class="form-control" id="expirationDate" name="expirationDate">
                <option value="1 anio" selected>1 año</option>
                <option value="1 mes">1 mes</option>
                <option value="1 semana">1 semana</option>
                <option value="1 dia">1 día</option>
                <option value="1 hora">1 hora</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="responseDelay" class="form-label">Tiempo de demora de la respuesta (segundos)</label>
            <input  type="number" name="responseDelay" class="form-control" id="responseDelay"  placeholder="Ingrese el tiempo de demora de la respuesta" min="0.00" value="${mockup.responseDelay}" step="0.01">
        </div>

        <#if mockup.jwtEnabled == false>
            <div class="form-check">
                <input type="checkbox" class="form-check-input" id="jwtEnabled" name="jwtEnabled">
                <label class="form-check-label" for="jwtEnabled">Habilitar validación vía JWT</label>
            </div>
        </#if>
        <#if mockup.jwtEnabled == true>
        <div class="form-check">
            <input checked type="checkbox" class="form-check-input" id="jwtEnabled" name="jwtEnabled">
            <label class="form-check-label" for="jwtEnabled">Habilitar validación vía JWT</label>
        </div>
        </#if>

        <button type="submit" class="btn btn-primary mt-3">Crear</button>
        <a href="/listar-mockup" class="btn btn-secondary mt-3">Cancelar</a>
    </form>
</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>