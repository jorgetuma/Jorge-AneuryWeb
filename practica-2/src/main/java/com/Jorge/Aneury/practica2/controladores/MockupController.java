package com.Jorge.Aneury.practica2.controladores;

import com.Jorge.Aneury.practica2.entidades.Mockup;
import com.Jorge.Aneury.practica2.servicios.MockupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class MockupController {

    private MockupService mockupService;

    @Autowired
    public MockupController(MockupService mockupService) {
        this.mockupService = mockupService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("mockups", mockupService.getAllMockups());
        return "/listar-mockup";
    }

    @RequestMapping("/{id}")
    public String getMockup(@PathVariable UUID id, Model model) {
        model.addAttribute("mockup", mockupService.getMockupById(id));
        return "ver-mockup";
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
                            @RequestParam String description,
                            @RequestParam String expirationDate,
                            @RequestParam float responseDelay,
                            @RequestParam(required = false) boolean jwtEnabled) {
        Mockup mockup = new Mockup();
        mockup.setName(name);
        mockup.setHttpMethod(httpMethod);
        mockup.setHeaders(headers);
        mockup.setResponseCode(responseCode);
        mockup.setContentType(contentType);
        mockup.setResponseBody(responseBody);
        mockup.setDescription(description);
        mockup.setResponseDelay(responseDelay);
        mockup.setJwtEnabled(jwtEnabled);

        Date currentDate = new Date();
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

}
