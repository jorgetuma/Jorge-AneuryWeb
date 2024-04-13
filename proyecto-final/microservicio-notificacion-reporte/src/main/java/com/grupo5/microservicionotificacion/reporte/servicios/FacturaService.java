package com.grupo5.microservicionotificacion.reporte.servicios;

import com.google.gson.Gson;
import com.grupo5.microservicionotificacion.reporte.dto.CarritoCompra;
import com.grupo5.microservicionotificacion.reporte.dto.Libro;
import com.grupo5.microservicionotificacion.reporte.colecciones.Factura;
import com.grupo5.microservicionotificacion.reporte.repositorios.FacturaRepository;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.web.client.RestClient;

@Service
public class FacturaService {
    private final FacturaRepository facturaRepository;
    private final Gson gson;

    @Autowired
    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
        this.gson = new Gson();
    }

    public List<Factura> listar() {
        return facturaRepository.findAll();
    }

    public List<Factura> listarByUsuario(String id) {
        return facturaRepository.findAllByIdUsuario(id);
    }

    public Factura buscarById(String id) {
        return facturaRepository.findFacturaById(id);
    }

    public Factura generar(String idUsuario,float total) {
        Factura factura = new Factura(idUsuario,total,new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()));
       List<Libro> libros = obtenerLibrosByCarrito(idUsuario);
       factura.setLibros(libros);
       facturaRepository.insert(factura);
       return factura;
    }

    public JasperPrint generarReporte(Factura factura) {
        List<Libro> libros = factura.getLibros();
        float totalFactura = factura.getTotal();
        List<String> nombresLibros = new ArrayList<>();
        List<Float> preciosLibros = new ArrayList<>();
        JasperPrint print = null;

        for (Libro libro : libros) {
            nombresLibros.add(libro.getTitulo());
            preciosLibros.add(libro.getPrecio());
        }

        // Parámetros del informe
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", "Factura");
        parametros.put("idFactura", factura.getId());
        parametros.put("nombreUsuario", "Cliente");
        parametros.put("totalFactura", totalFactura);
        parametros.put("nombresLibros", nombresLibros);
        parametros.put("preciosLibros", preciosLibros);

        // Compilación y generación del reporte
        try {
            JasperReport reporte = JasperCompileManager.compileReport("/reporte/factura.jrxml");
             print = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(print, "/reporte/factura" + factura.getId() + ".pdf");
        } catch (JRException e) {
            e.printStackTrace();
        }
        return print;
    }

    private List<Libro> obtenerLibrosByCarrito(String idUsuario) {
        List<Libro> libros = new ArrayList<>();
        RestClient client = RestClient.create(); // Cliente HTTP para llamar a las API de otros micro-servicios
        String carritoJson = client.get()
                .uri("http://localhost:8082/carrito/buscar-usuario/" + idUsuario)
                .retrieve()
                .body(String.class);

        CarritoCompra carritoCompra = gson.fromJson(carritoJson,CarritoCompra.class);

        for(int i = 0; i < carritoCompra.getLibros().size();i++) {
            String idLibro = carritoCompra.getLibros().get(i);
            String libroJson = client.get()
                    .uri("http://localhost:8081/catalogo/buscar/" + idLibro)
                    .retrieve()
                    .body(String.class);
            Libro libro = gson.fromJson(libroJson,Libro.class);
            libros.add(libro);
        }
        return libros;
    }
}
