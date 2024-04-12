package com.geolocate.api.controller;

import com.geolocate.service.api.PingApi;
import com.geolocate.service.model.Ping200Response;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@OpenAPIDefinition(servers = {@Server(url = "/geolocatesvc", description = "Geolocate Service")})
public class PingController implements PingApi {

    @Override
    public Mono<ResponseEntity<Ping200Response>> ping(ServerWebExchange exchange){
        return Mono.just(ResponseEntity.ok(Ping200Response.builder().message("Welcome to Geolocate Service!").build()));
    }
}
