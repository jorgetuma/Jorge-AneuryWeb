<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <!-- Enlace al archivo CSS de Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<h1>Vista del Dashboard</h1>

<div class="container border rounded my-3">
    <canvas id="temperatureChart"></canvas>
</div>

<div class="container border rounded my-3">
    <canvas id="humidityChart" ></canvas>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

<script>
    // Crear arreglos para datos de temperatura y humedad
    var fechas = [];
    var temperaturas = [];
    var humedades = [];

    // Procesar las tramas JSON y llenar los arreglos de datos
    <#list tramasJSON as trama>

        fechas.push("${trama.fechaGeneracion}");
        temperaturas.push("${trama.temperatura}");
        humedades.push("${trama.humedad}");
    </#list>


    // Crear gráfico de temperatura
    var temperatureCtx = document.getElementById('temperatureChart').getContext('2d');
    var temperatureChart = new Chart(temperatureCtx, {
        type: 'line',
        data: {
            labels: fechas,
            datasets: [{
                label: 'Temperatura (°C)',
                data: temperaturas,
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1,
                fill: false
            }]
        }
    });

    // Crear gráfico de humedad
    var humidityCtx = document.getElementById('humidityChart').getContext('2d');
    var humidityChart = new Chart(humidityCtx, {
        type: 'line',
        data: {
            labels: fechas,
            datasets: [{
                label: 'Humedad (%)',
                data: humedades,
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1,
                fill: false
            }]
        }
    });

    const socket = new SockJS('/ws-message');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, (frame) => {
        console.log('Connected: ' + frame);
        // Subscribe to the topic for receiving updates
        stompClient.subscribe('/topic/message', (message) => {
            console.log(message.body);
            const tramaJSON = JSON.parse(message.body);
            updateCharts(tramaJSON);
        });
    } , (error) => {
        console.log('Error: ' + error);
    });

    function updateCharts(tramaJSON) {
        const fecha = tramaJSON.fechaGeneracion;
        const temperatura = tramaJSON.temperatura;
        const humedad = tramaJSON.humedad;

        temperatureChart.data.labels.push(fecha);
        temperatureChart.data.datasets[0].data.push(temperatura);
        temperatureChart.update();

        humidityChart.data.labels.push(fecha);
        humidityChart.data.datasets[0].data.push(humedad);
        humidityChart.update();
    }

</script>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>
