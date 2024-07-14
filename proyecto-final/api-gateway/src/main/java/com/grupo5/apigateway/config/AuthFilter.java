package com.grupo5.apigateway.config;

import com.grupo5.apigateway.dto.RequestDto;
import com.grupo5.apigateway.dto.TokenDto;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final WebClient.Builder webClient;

    public AuthFilter(WebClient.Builder webClient) {
        super(Config.class);
        this.webClient = webClient;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, HttpStatus.BAD_REQUEST, "Missing Authorization Header");
            }
            String tokenHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            assert tokenHeader != null;
            String [] chunks = tokenHeader.split(" ");
            if (chunks.length != 2 || !chunks[0].equals("Bearer")) {
                return onError(exchange, HttpStatus.UNAUTHORIZED, "Invalid Authorization Header");
            }
            return validateToken(chunks[1], exchange)
                    .flatMap(valid -> {
                        if (valid) {
                            return chain.filter(exchange);
                        } else {
                            return onError(exchange, HttpStatus.UNAUTHORIZED, "Invalid Token");
                        }
                    });
        }));
    }

    private Mono<Boolean> validateToken(String token, ServerWebExchange exchange) {
        return webClient.build()
                .post()
                .uri("http://microservicio-autenticacion/auth/validate?token=" + token)
                .bodyValue(new RequestDto(exchange.getRequest().getPath().toString(), exchange.getRequest().getMethod().toString()))
                .retrieve()
                .bodyToMono(TokenDto.class)
                .map(TokenDto::isValid) // Suponiendo que TokenDto tiene un método isValid()
                .onErrorReturn(false);  // En caso de error, retornamos false para indicar que la validación falló
    }

    public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        DataBufferFactory bufferFactory = response.bufferFactory();
        DataBuffer buffer = bufferFactory.wrap(message.getBytes());
        return response.writeWith(Mono.just(buffer));
    }


    public static class Config{}
}
