package com.spring.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    private AuthFilter authFilter;
    @Bean
    public RouteLocator configLocal(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/api/v1.0.0/payment/*").filters(f->f.filter(authFilter)).
                        uri("http://localhost:8085"))
                .route(r->r.path("/api/v1.0.0/client/*").filters(f->f.filter(authFilter)).uri("http://localhost:8080"))
                .route(r->r.path("/api/v1.0.0/product/*").filters(f->f.filter(authFilter)).uri("http://localhost:8084"))
                .route(r->r.path("/api/v1.0.0/order/**").filters(f->f.filter(authFilter)).uri("http://localhost:8082"))
                .route(r->r.path("/api/v1.0.0/auth/**").uri("http://localhost:9091"))
                .build();
    }
}
