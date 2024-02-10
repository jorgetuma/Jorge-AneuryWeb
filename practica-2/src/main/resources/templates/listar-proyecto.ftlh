<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Proyectos</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h1>Listado de Proyectos</h1>
    <hr>

    <button type="button" class="btn btn-primary mb-3" data-toggle="modal" data-target="#crearProyectoModal">
        Crear Proyecto
    </button>

    <#if size!=0>
        <#list proyectos as p>
            <ul class="list-group">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    Nombre: ${p.nombre}
                    <span class="badge badge-primary badge-pill">descripción: ${p.descripcion}</span>
                    <span class="badge badge-primary badge-pill">Usuario: ${p.userName}</span>
                    <div>
                        <a href="/proyecto/modificar-proyecto/${p.id}"><button class="btn btn-primary btn-sm mr-2">Modificar</button></a>
                        <a href="/proyecto/eliminar-proyecto/${p.id}"><button class="btn btn-danger btn-sm">Eliminar</button></a>
                    </div>
                </li>
            </ul>
        </#list>
    </#if>

    <div>
        <#if pag <= cantpag>
            <a href="/proyecto/listar-proyecto/${pag + 1}" class="btn btn-primary">Siguiente</a>
        </#if>

        <#if pag!=1>
            <a href="/proyecto/listar-proyecto/${pag - 1}" class="btn btn-primary">Anterior</a>
        </#if>
    </div>

    <!-- Modal para crear un nuevo proyecto -->
    <div class="modal fade" id="crearProyectoModal" tabindex="-1" role="dialog" aria-labelledby="crearProyectoModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="crearProyectoModalLabel">Crear Nuevo Proyecto</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="/proyecto/crear-proyecto" method="post">
                        <div class="form-group">
                            <label for="nombre">Nombre del Proyecto</label>
                            <input type="text" class="form-control" id="nombre" name="nombre">
                        </div>

                        <div class="form-group">
                            <label for="descripcion">Descripción del Proyecto</label>
                            <input type="text" class="form-control" id="descripcion" name="descripcion">
                        </div>

                        <div class="form-group">
                            <label for="username">Usuario Asociado</label>
                            <input type="text" class="form-control" id="username" name="username">
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-primary">Crear Proyecto</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

