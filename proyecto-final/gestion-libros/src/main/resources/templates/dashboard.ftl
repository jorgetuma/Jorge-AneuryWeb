<#include "layout.ftl">
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">G5books</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/">Comprar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Carrito de compras</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/compras/listar/userid">Compras realizadas</a>
                </li>
                <li class="nav-iterm" >
                    <a class="nav-link" href="/admin/dashboard">Administrar</a>
                </li>
            </ul>
            <form class="d-flex" role="search">
                <button class="btn btn-outline-success" type="submit" formaction="#">Login</button>
                <button class="btn btn-outline-success" type="submit" formaction="#">Registrarse</button>
                <button class="btn btn-outline-success" type="submit" formaction="#">Cerrar sesión</button>
            </form>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h1>Dashboard del día ${fecha}</h1>
    <div class="row">
        <div class="col-md-6">
            <h2>Compras totales</h2>
            <canvas id="comprasChart"></canvas>
        </div>
        <div class="col-md-6">
            <h2>Compras por estado</h2>
            <canvas id="pendientesChart"></canvas>
        </div>
    </div>
</div>

<div class="container mt-2">
    <a href="#"><button type="button" class="btn btn-primary">Usuarios y perfiles</button></a>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.0/dist/chart.min.js"></script>
<script>

    const datosCompras = [${compras}];
    const datosPendientes = [${pendientes},${realizadas}]

    // Configuración de los gráficos
    const configCompras = {
        type: 'bar',
        data: {
            labels: ['Compras totales'],
            datasets: [{
                label: 'Compras realizadas',
                data: datosCompras,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.5)',
                    'rgba(54, 162, 235, 0.5)',
                ]
            }]
        }
    };

    const configPendientes = {
        type: 'pie',
        data: {
            labels: ['pendientes','realizadas'],
            datasets: [{
                label: 'Compras pendientes',
                data: datosPendientes,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.5)',
                    'rgba(54, 162, 235, 0.5)',
                ]
            }]
        }
    };

    let ctxCompras = document.getElementById('comprasChart').getContext('2d');
    let ctxPendientes = document.getElementById('pendientesChart').getContext('2d');

    let comprasChart = new Chart(ctxCompras, configCompras);
    let pendientesChart = new Chart(ctxPendientes, configPendientes);
</script>

</body>
