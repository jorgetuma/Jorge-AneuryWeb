package com.grupo5.practica7.funciones;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.grupo5.practica7.encapsulaciones.Reserva;
import com.grupo5.practica7.servicios.ReservaDynamoDbService;

import java.util.HashMap;

public class FuncionCRUDReserva implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private final ReservaDynamoDbService reservaDynamoDbService = new ReservaDynamoDbService();
    private final Gson gson = new Gson();
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        Reserva reserva;

        context.getLogger().log("Petición: "+gson.toJson(request));

        String metodoHttp = request.getHttpMethod();

        context.getLogger().log("Metodo de acceso: "+metodoHttp);
        context.getLogger().log("Parametros enviados: "+request.getPathParameters());
        context.getLogger().log("Cuerpo enviado: "+request.getBody());

        String salida = "";
        switch (metodoHttp) {
            case "GET":
                ReservaDynamoDbService.ListarReservaResponse listarReservaResponse = reservaDynamoDbService.listarReservas(null,context);
                salida = gson.toJson(listarReservaResponse);
                break;
            case "POST":
            case "PUT":
                reserva = gson.fromJson(request.getBody(),Reserva.class);
                reservaDynamoDbService.insertar(reserva,context);
                salida = gson.toJson(reserva);
                break;
            case "DELETE":
                reserva = gson.fromJson(request.getBody(),Reserva.class);
                reservaDynamoDbService.eliminar(reserva,context);
                salida = gson.toJson(reserva);
                break;
        }

        //Headers
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        //Salida
        APIGatewayProxyResponseEvent reponse = new APIGatewayProxyResponseEvent();
        reponse.setStatusCode(200);
        reponse.setHeaders(headers);
        reponse.setBody(salida);

        return reponse;
    }
}
