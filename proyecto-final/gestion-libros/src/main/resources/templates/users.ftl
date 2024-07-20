<#include "layout.ftl">
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
                    <a class="nav-link" href="/carrito/${userId}">Carrito de compras</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/compras/listar/${userId}">Compras realizadas</a>
                </li>
                <li class="nav-item" >
                    <a class="nav-link" href="/admin/dashboard">Administrar</a>
                </li>
                <li class="nav-item" >
                    <a class="nav-link active" href="/admin/users">Usuarios</a>
                </li>
            </ul>
            <a class="btn btn-outline-success" href="/logout">cerrar sesión</a>
        </div>
    </div>
</nav>

<main class="container">
    <div class="d-flex justify-content-between align-items-center">
        <h3>Usuarios</h3>
        <button class="btn btn-primary m-3" data-bs-toggle="modal" data-bs-target="#createUserModal">
            <div class="d-flex align-items-center">
                Crear<span class="material-symbols-outlined ms-1">add_circle</span>
            </div>
        </button>
    </div>

    <div class="table-responsive card p-3">
        <table class="table table-hover" id="usersTable">
            <thead>
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Email</th>
                <th>Rol</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>
                        <button class="btn px-1 py-0" data-bs-toggle="modal" data-bs-target="#updateModal${user.id}">
                            <span class="material-symbols-outlined text-primary">edit</span>
                        </button>
                        <button class="btn px-1 py-0" data-bs-toggle="modal" data-bs-target="#deleteModal${user.id}">
                            <span class="material-symbols-outlined text-danger">delete</span>
                        </button>
                    </td>
                </tr>

                <!-- Modal para modificar usuario -->
                <div class="modal fade" id="updateModal${user.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">Modificar Usuario</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form id="updateUserForm${user.id}" class="needs-validation" method="post" action="updateUser/${user.id}" novalidate>
<#--                                    <input type="hidden" name="_method" value="put">-->
                                    <div class="mb-3">
                                        <label for="name${user.id}" class="form-label">Nombre</label>
                                        <input type="text" class="form-control" name="name" id="name${user.id}" required value="${user.name}">
                                        <div class="invalid-feedback">
                                            Por favor, ingrese un nombre.
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="email${user.id}" class="form-label">Email</label>
                                        <input type="email" class="form-control" name="email" id="email${user.id}" required value="${user.email}">
                                        <div class="invalid-feedback">
                                            Por favor, ingrese un email válido.
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="role${user.id}" class="form-label">Rol</label>
                                        <select class="form-select" name="role" id="role${user.id}" required>
                                            <option value="" >Seleccione un rol</option>
                                            <option value="USER" <#if user.role == "USER">selected</#if> >User</option>
                                            <option value="ADMIN" <#if user.role == "ADMIN">selected</#if> >Admin</option>
                                        </select>
                                        <div class="invalid-feedback">
                                            Por favor, seleccione un rol.
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-end">
                                        <button type="submit" class="btn btn-primary my-3">Modificar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

<#--               Modal para eliminar usuario-->
                <div class="modal fade" id="deleteModal${user.id}" tabindex="-1" aria-labelledby="deleteModalLabel${user.id}" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="deleteModalLabel${user.id}">Eliminar Usuario</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <p>¿Está seguro de que desea eliminar al usuario ${user.name}?</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                <form method="post" action="deleteUser/${user.id}" class="d-inline">
                                    <button type="submit" class="btn btn-danger">Eliminar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
            </tbody>
        </table>
    </div>

    <!-- Modal para crear usuario -->
    <div class="modal fade" id="createUserModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Crear Usuario</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="createUserForm" class="needs-validation" method="post" action="users" novalidate>
                        <div class="mb-3">
                            <label for="name" class="form-label">Nombre</label>
                            <input type="text" class="form-control" name="name" id="name" required>
                            <div class="invalid-feedback">
                                Por favor, ingrese un nombre.
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" name="email" id="email" required>
                            <div class="invalid-feedback">
                                Por favor, ingrese un email válido.
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Contraseña</label>
                            <input type="password" class="form-control" name="password" id="password" required autocomplete="new-password">
                            <div class="invalid-feedback">
                                Por favor, ingrese una contraseña.
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="confirmPassword" class="form-label">Confirme Contraseña</label>
                            <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" required autocomplete="new-password">
                            <div class="invalid-feedback">
                                Por favor, ingrese una contraseña.
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="role" class="form-label">Rol</label>
                            <select class="form-select" name="role" id="role" required>
                                <option value="" selected>Seleccione un rol</option>
                                <option value="USER">User</option>
                                <option value="ADMIN">Admin</option>
                            </select>
                            <div class="invalid-feedback">
                                Por favor, seleccione un rol.
                            </div>
                        </div>
                        <div class="d-flex justify-content-end">
                            <button type="submit" class="btn btn-primary my-3">Crear</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</main>
<script>
    // JavaScript for Bootstrap 5 validation
    (function () {
        'use strict'

        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.querySelectorAll('.needs-validation')

        // Loop over them and prevent submission
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }

                    form.classList.add('was-validated')
                }, false)
            })
    })()
</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<#--<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-kXNVqIvZh9j50UAnbFrjPT9O2iNpt5fNr1DHCvKuJdRjBLdqV5NHb+8+8AQy50mp" crossorigin="anonymous"></script>-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>

<script>
    $(document).ready( function () {
        $('#usersTable').DataTable({
            language: {
                info: "Mostrando _START_-_END_ de _TOTAL_ usuarios en total",
                infoEmpty: 'Lista de usuarios vacía',
                infoFiltered: '(filtered from _MAX_ total records)',
                lengthMenu: 'Display _MENU_ records per page',
                emptyTable: 'La lista está vacía',
                search: "Buscar:"
            },
            ordering:  false,
            paging: false
        });
    });

</script>