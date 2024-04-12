package com.geolocate.api.controller;

import com.geolocate.api.service.GoogleService;
import com.geolocate.service.api.AddressApi;
import com.geolocate.service.model.DataResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
@OpenAPIDefinition(servers = {@Server(url = "/geolocatesvc", description = "Geolocate Service")})
public class AddressApiController implements AddressApi {

    @Autowired
    private GoogleService googleService;

    @Override
    public Mono<ResponseEntity<DataResponse>> addressLocate(String latitude, String longitude, ServerWebExchange exchange) {
        return googleService.getGoogleApi(latitude, longitude)
                .map(googleData -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(
                        // Show only the address in the response
                        DataResponse.builder().address(googleData.getAddress()).build()
                ))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

}
