package org.grupo5;

import org.grupo5.encapsulaciones.Cliente;

public class Main {
    public static void main(String[] args) throws Exception{
        // Simulando cliente para enviar datos a los suscriptores
        Cliente cliente = new Cliente();
        cliente.generarDatos(60000);
    }
}