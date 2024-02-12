package com.Jorge.Aneury.practica2.controladores;

import com.Jorge.Aneury.practica2.entidades.Mockup;
import com.Jorge.Aneury.practica2.servicios.MockupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
public class MockupController {

    private MockupService mockupService;

    @Autowired
    public MockupController(MockupService mockupService) {
        this.mockupService = mockupService;
    }

    @RequestMapping(value = "/{project}/{id}")
    public ResponseEntity<?> getMockup(@PathVariable UUID id, String project, HttpServletRequest request) throws InterruptedException {
        String requestMethod = request.getMethod();
        Mockup mockup = mockupService.getMockupById(id);

        if (mockup != null && requestMethod.equalsIgnoreCase(mockup.getHttpMethod())) {
            HttpHeaders headers = new HttpHeaders();

            try {
                // Convertir la cadena JSON de headers a un mapa Java
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> headerMap = objectMapper.readValue(mockup.getHeaders(), Map.class);

                // Añadir cada header al HttpHeaders
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    headers.add(entry.getKey(), entry.getValue());
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error");
            }

            String contentType = mockup.getContentType();
            long delay = (long) (mockup.getResponseDelay() * 1000);

            if (contentType.equals("application/json")) {
                TimeUnit.MICROSECONDS.sleep( delay * 1000);
                return ResponseEntity.status(mockup.getResponseCode())
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mockup.getResponseBody());
            } else if (contentType.equals("text/html")) {
                TimeUnit.MICROSECONDS.sleep( delay);
                return ResponseEntity.status(mockup.getResponseCode())
                        .headers(headers)
                        .contentType(MediaType.TEXT_HTML)
                        .body(mockup.getResponseBody());
            } else if (contentType.equals("application/xml")) {
                TimeUnit.MICROSECONDS.sleep( delay);
                return ResponseEntity.status(mockup.getResponseCode())
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_XML)
                        .body(mockup.getResponseBody());
            } else if (contentType.equals("text/plain")) {
                TimeUnit.MICROSECONDS.sleep( delay );
                return ResponseEntity.status(mockup.getResponseCode())
                        .headers(headers)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(mockup.getResponseBody());
            } else {
                TimeUnit.MICROSECONDS.sleep( delay );
                return ResponseEntity.ok().headers(headers).body(mockup.getResponseBody());
            }

        } else {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method Not Allowed");
        }
    }

}
