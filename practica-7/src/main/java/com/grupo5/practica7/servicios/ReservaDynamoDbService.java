package com.grupo5.practica7.servicios;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.grupo5.practica7.encapsulaciones.Reserva;

import java.util.List;

public class ReservaDynamoDbService {

    public ReservaResponse insertar(Reserva reserva, Context context) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        if(reserva.getId().isEmpty() || reserva.getNombre().isEmpty() || reserva.getCorreo() .isEmpty()|| reserva.getLaboratorio().isEmpty()) {
            throw new RuntimeException("Datos enviados no son validos");
        }

        try {
            mapper.save(reserva);
        }catch (Exception e) {
            return  new ReservaResponse(true,e.getMessage(),null);
        }
        return new ReservaResponse(false,null,reserva);
    }

    public ListarReservaResponse listarReservas(FiltroListarReserva filtro,Context context) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        List<Reserva> reservas = mapper.scan(Reserva.class,new DynamoDBScanExpression());

        return  new ListarReservaResponse(false,"",reservas);
    }

    public ReservaResponse eliminar(Reserva reserva,Context context) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        mapper.delete(reserva);

        return new ReservaResponse(false,null,reserva);
    }

    public static  class ListarReservaResponse {
        boolean error;
        String mensajeError;
        List<Reserva> reservas;

        public ListarReservaResponse() {

        }

        public ListarReservaResponse(boolean error, String mensajeError, List<Reserva> reservas) {
            this.error = error;
            this.mensajeError = mensajeError;
            this.reservas = reservas;
        }

        public boolean isError() {
            return error;
        }

        public void setError(boolean error) {
            this.error = error;
        }

        public String getMensajeError() {
            return mensajeError;
        }

        public void setMensajeError(String mensajeError) {
            this.mensajeError = mensajeError;
        }

        public List<Reserva> getReservas() {
            return reservas;
        }

        public void setReservas(List<Reserva> reservas) {
            this.reservas = reservas;
        }
    }

    public static class ReservaResponse {
        boolean error;
        String mensajeError;
        Reserva reserva;

        public ReservaResponse() {

        }

        public ReservaResponse(boolean error, String mensajeError, Reserva reserva) {
            this.error = error;
            this.mensajeError = mensajeError;
            this.reserva = reserva;
        }

        public boolean isError() {
            return error;
        }

        public void setError(boolean error) {
            this.error = error;
        }

        public String getMensajeError() {
            return mensajeError;
        }

        public void setMensajeError(String mensajeError) {
            this.mensajeError = mensajeError;
        }

        public Reserva getReserva() {
            return reserva;
        }

        public void setReserva(Reserva reserva) {
            this.reserva = reserva;
        }
    }

    public static class FiltroListarReserva {
        String filtro;

        public String getFiltro() {
            return filtro;
        }

        public void setFiltro(String filtro) {
            this.filtro = filtro;
        }
    }
}
