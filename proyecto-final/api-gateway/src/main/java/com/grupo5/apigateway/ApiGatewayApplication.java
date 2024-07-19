package com.grupo5.apigateway;

import com.grupo5.apigateway.config.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Autowired
	private AuthFilter authFilter;

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("microservicio-autenticacion", r -> r.path("/auth/**")
						.uri("lb://microservicio-autenticacion"))

				.route("microservicio-autenticacion", r -> r.path("/users/**")
						.filters(f -> f.filter(authFilter.apply(new AuthFilter.Config())))
						.uri("lb://microservicio-autenticacion"))

				.route("microservicio-notificacion-reporte", r -> r.path("/notificacion/**")
						.uri("lb://microservicio-notificacion-reporte"))

				.route("microservicio-catalogo", r -> r.path("/catalogo/**")
						.filters(f -> f.filter(authFilter.apply(new AuthFilter.Config())))
						.uri("lb://microservicio-catalogo"))

				.route("gestion-libros", r -> r.path("/login")
//						.filters(f -> f.filter(authFilter.apply(new AuthFilter.Config())))
						.uri("lb://gestion-libros"))
				.route("gestion-libros", r -> r.path("/admin/**")
						.filters(f -> f.filter(authFilter.apply(new AuthFilter.Config())))
						.uri("lb://gestion-libros"))

				.route("microservicio-carritocompras", r -> r.path("/carrito/**")
						.uri("lb://microservicio-carritocompras"))
				.build();
	}

}
