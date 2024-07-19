package com.grupo5.microservicioautenticacion.config;

import com.grupo5.microservicioautenticacion.dto.RequestDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "admin-paths")
public class RouteValidator {
    private List<RequestDto> paths;

    public boolean isAdminPath(RequestDto requestDto) {
        return paths.stream().anyMatch(p ->
                Pattern.compile(p.getUri()).matcher(requestDto.getUri()).matches() && p.getMethod().equals(requestDto.getMethod()));
//                p.getUri().equals(requestDto.getUri()) && p.getMethod().equals(requestDto.getMethod()));
//                Pattern.matches(p.getUri(), requestDto.getUri()) && p.getMethod().equals(requestDto.getMethod()));
    }
}
