package com.Jorge.Aneury.practica2.controladores;

import com.Jorge.Aneury.practica2.entidades.Mockup;
import com.Jorge.Aneury.practica2.entidades.Proyecto;
import com.Jorge.Aneury.practica2.servicios.MockupService;
import com.Jorge.Aneury.practica2.servicios.ProyectoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.*;

@Controller
@RequestMapping("/")
public class MockupCrudController {

    private MockupService mockupService;
    private ProyectoService proyectoService;

    @Autowired
    public MockupCrudController(MockupService mockupService, ProyectoService proyectoService) {
        this.mockupService = mockupService;
        this.proyectoService = proyectoService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("mockups", mockupService.getAllMockups());
        return "/listar-mockup";
    }

    @GetMapping("listar-mockup")
    public String listarMackups(Model model) {
        model.addAttribute("mockups", mockupService.getAllMockups());
        return "/listar-mockup";
    }

    @GetMapping("crear-mockup")
    public String mockupForm(Model model) {
        model.addAttribute("projects", proyectoService.getProyectos());
        return "/crear-mockup";
    }

    @PostMapping("crear-mockup")
    public String crearMockup(@RequestParam String name,
                            @RequestParam String httpMethod,
                            @RequestParam String headers,
                            @RequestParam int responseCode,
                            @RequestParam String contentType,
                            @RequestParam String responseBody,
                            @RequestParam String expirationDate,
                            @RequestParam float responseDelay,
                            @RequestParam(required = false) boolean jwtEnabled,
                            @RequestParam int project,
                            Model model) {

        // Verify if headersJson is in valid JSON format
        List<Proyecto> projects = proyectoService.getProyectos();
        boolean isValidJson = isValidJson(headers);
        if (!isValidJson) {
            // Handle the case where headersJson is not in valid JSON format
            model.addAttribute("errorHeaders", "El header debe ser un JSON válido");
            model.addAttribute("projects", projects);
            return "crear-mockup";
        }

        // Verifica si el Content-Type es application/json y valida que el cuerpo de la respuesta sea JSON
        if ("application/json".equals(contentType) && !isValidJson(responseBody)) {
            model.addAttribute("errorResponseBody", "El cuerpo de la respuesta debe ser un JSON válido");
            model.addAttribute("projects", projects);
            return "crear-mockup";
        }
        // Verifica si el Content-Type es application/xml y valida que el cuerpo de la respuesta sea XML
        if ("application/xml".equals(contentType) && !isValidXml(responseBody)) {
            model.addAttribute("errorResponseBody", "El cuerpo de la respuesta debe ser XML válido");
            model.addAttribute("projects", projects);
            return "crear-mockup";
        }

        Mockup mockup = new Mockup();
        mockup.setName(name);
        mockup.setHttpMethod(httpMethod);
        mockup.setHeaders(headers);
        mockup.setResponseCode(responseCode);
        mockup.setContentType(contentType);
        mockup.setResponseBody(responseBody);
        mockup.setResponseDelay(responseDelay);
        mockup.setJwtEnabled(jwtEnabled);
        mockup.setProject(proyectoService.getProyectoById(project));

        Date currentDate = new Date();
        mockup.setCreatedDate(currentDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        if (expirationDate.equals("1 anio")) {
            calendar.add(Calendar.YEAR, 1);
            mockup.setExpirationDate(calendar.getTime());
        } else if (expirationDate.equals("1 mes")) {
            calendar.add(Calendar.MONTH, 1);
            mockup.setExpirationDate(calendar.getTime());
        } else if (expirationDate.equals("1 semana")) {
            calendar.add(Calendar.WEEK_OF_MONTH, 1);
            mockup.setExpirationDate(calendar.getTime());
        } else if (expirationDate.equals("1 dia")) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            mockup.setExpirationDate(calendar.getTime());
        } else if (expirationDate.equals("1 hora")) {
            calendar.add(Calendar.HOUR, 1);
            mockup.setExpirationDate(calendar.getTime());
        }

        mockupService.save(mockup);
        return "redirect:/";
    }

    private boolean isValidJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.readTree(json);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    private boolean isValidXml(String xml) {
        try {
            DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
