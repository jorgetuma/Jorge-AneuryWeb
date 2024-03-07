package com.Jorge.Aneury.practica6.controladores;

import com.Jorge.Aneury.practica6.entidades.Mockup;
import com.Jorge.Aneury.practica6.servicios.JwtService;
import com.Jorge.Aneury.practica6.servicios.MockupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
public class MockupController {

    private MockupService mockupService;
    private JwtService jwtService;

    @Autowired
    public MockupController(MockupService mockupService, JwtService jwtService) {
        this.mockupService = mockupService;
        this.jwtService = jwtService;
    }

    @RequestMapping(value = "/{project}/{id}")
    public ResponseEntity<?> getMockup(@PathVariable UUID id, String project, HttpServletRequest request) throws InterruptedException {
        String requestMethod = request.getMethod();
        Mockup mockup = mockupService.getMockupById(id);
        if (mockup.getExpirationDate().after(new Date())) {
            if (mockup != null && requestMethod.equalsIgnoreCase(mockup.getHttpMethod())) {
                HttpHeaders headers = new HttpHeaders();

                if (mockup.isJwtEnabled()) {
                    String jwt = extractJwtFromRequest(request);
                    if (StringUtils.hasText(jwt)) {
                        if (!jwtService.validateToken(jwt)) {
                            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
                        }
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
                    }
                }

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
                    TimeUnit.MICROSECONDS.sleep(delay * 1000);
                    return ResponseEntity.status(mockup.getResponseCode())
                            .headers(headers)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mockup.getResponseBody());
                } else if (contentType.equals("text/html")) {
                    TimeUnit.MICROSECONDS.sleep(delay);
                    return ResponseEntity.status(mockup.getResponseCode())
                            .headers(headers)
                            .contentType(MediaType.TEXT_HTML)
                            .body(mockup.getResponseBody());
                } else if (contentType.equals("application/xml")) {
                    TimeUnit.MICROSECONDS.sleep(delay);
                    return ResponseEntity.status(mockup.getResponseCode())
                            .headers(headers)
                            .contentType(MediaType.APPLICATION_XML)
                            .body(mockup.getResponseBody());
                } else if (contentType.equals("text/plain")) {
                    TimeUnit.MICROSECONDS.sleep(delay);
                    return ResponseEntity.status(mockup.getResponseCode())
                            .headers(headers)
                            .contentType(MediaType.TEXT_PLAIN)
                            .body(mockup.getResponseBody());
                } else {
                    TimeUnit.MICROSECONDS.sleep(delay);
                    return ResponseEntity.ok().headers(headers).body(mockup.getResponseBody());
                }

            } else {
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method Not Allowed");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mockup Not Found");
        }
    }

    @RequestMapping("test/{id}")
    public ResponseEntity<String> test(@PathVariable UUID id, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException, ServletException {
        Mockup mockup = mockupService.getMockupById(id);

        if (!request.getMethod().equalsIgnoreCase(mockup.getHttpMethod())) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method Not Allowed");
        }

        StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();
        String baseUrl = url.substring(0, url.indexOf(uri));

        String fullUrl = baseUrl + "/" + mockup.getProject().getId() + "/" + mockup.getId();

        HttpMethod httpMethod = HttpMethod.valueOf(mockup.getHttpMethod());
        HttpHeaders headers = new HttpHeaders();
        if (mockup.isJwtEnabled()){
            headers.add("Authorization", "Bearer " + mockup.getJwtToken());
        }
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(fullUrl, httpMethod, entity, String.class);
        return responseEntity;
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
