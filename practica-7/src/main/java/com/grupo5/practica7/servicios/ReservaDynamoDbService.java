package com.grupo5.practica7.servicios;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.lambda.runtime.Context;
import com.grupo5.practica7.encapsulaciones.Reserva;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReservaDynamoDbService {

    public ReservaResponse insertar(Reserva reserva, Context context) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        if (reserva.getId().isEmpty() || reserva.getNombre().isEmpty() || reserva.getCorreo().isEmpty() || reserva.getLaboratorio().isEmpty() || reserva.getFechaReserva().isEmpty()) {
            throw new RuntimeException("Datos enviados no son validos");
        }

        // Verificar si la reserva está dentro del horario permitido (de 8 am a 10 pm)
        Calendar cal;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = sdf.parse(reserva.getFechaReserva());
            cal = Calendar.getInstance();
            cal.setTime(date);
            int horaReserva = cal.get(Calendar.HOUR_OF_DAY); // Obtener la hora de la reserva
            if (horaReserva < 8 || horaReserva >= 22) {
                throw new RuntimeException("Las reservas solo están permitidas desde las 8 am hasta las 10 pm");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("El formato de fecha y hora no es válido. Se esperaba 'yyyy-MM-dd HH:mm:ss'.", e);
        }


        // Verificar si el laboratorio está lleno para la hora de la reserva
        int reservasEnHora = obtenerReservasEnHora(new Date(cal.getTime().getTime()), mapper);
        if (reservasEnHora >= 7) {
            throw new RuntimeException("El laboratorio está lleno para la hora de la reserva");
        }

        try {
            mapper.save(reserva);
        } catch (Exception e) {
            return new ReservaResponse(true, e.getMessage(), null);
        }
        return new ReservaResponse(false, null, reserva);
    }

    public ListarReservaResponse listarReservas(Context context) {
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

    // Método para obtener la cantidad de reservas en una hora específica
    private int obtenerReservasEnHora(Date fechaReserva, DynamoDBMapper mapper) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaReserva);
        int horaReserva = cal.get(Calendar.HOUR_OF_DAY);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date inicioHora = cal.getTime();
        cal.add(Calendar.HOUR_OF_DAY, 1);
        Date finHora = cal.getTime();

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":inicio", new AttributeValue().withS(inicioHora.toString()));
        expressionAttributeValues.put(":fin", new AttributeValue().withS(finHora.toString()));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("fecha BETWEEN :inicio AND :fin")
                .withExpressionAttributeValues(expressionAttributeValues);

        return mapper.scan(Reserva.class, scanExpression).size();
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
