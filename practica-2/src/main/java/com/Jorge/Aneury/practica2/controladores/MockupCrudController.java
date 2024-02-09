package com.Jorge.Aneury.practica2.controladores;

import com.Jorge.Aneury.practica2.entidades.Mockup;
import com.Jorge.Aneury.practica2.servicios.MockupService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/")
public class MockupCrudController {

    private MockupService mockupService;

    @Autowired
    public MockupCrudController(MockupService mockupService) {
        this.mockupService = mockupService;
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
    public String mockupForm() {
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
                            @RequestParam(required = false) boolean jwtEnabled) {
        Mockup mockup = new Mockup();
        mockup.setName(name);
        mockup.setHttpMethod(httpMethod);

        // Verify if headersJson is in valid JSON format
        boolean isValidJson = isValidJson(headers);
        if (!isValidJson) {
            // Handle the case where headersJson is not in valid JSON format
            return "Invalid JSON format for headers";
        }

        mockup.setHeaders(headers);

        mockup.setResponseCode(responseCode);
        mockup.setContentType(contentType);
        mockup.setResponseBody(responseBody);
        mockup.setResponseDelay(responseDelay);
        mockup.setJwtEnabled(jwtEnabled);

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

}
