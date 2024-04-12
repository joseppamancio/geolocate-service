package com.geolocate.api.service;

import com.geolocate.api.config.properties.Properties;
import com.geolocate.domain.model.Location;
import com.geolocate.domain.repository.LocationRepository;
import com.geolocate.service.model.DataResponse;
import com.geolocate.service.model.GoogleRaw;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;


@Slf4j
@Service
public class GoogleService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private Properties properties;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LocationRepository locationRepository;

    public Mono<DataResponse> getGoogleApi(String latitude, String longitude) {

        String googleApiUrl = properties.getGoogleUrl() + "?key=" + googleApiKeyValidation(properties.getGoogleApiKey()) +
                "&latlng=" + latitude + "," + longitude;

        return webClient
                .method(HttpMethod.GET)
                .uri(googleApiUrl).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(GoogleRaw.class)
                .map(googleRaw -> extractResponse(googleRaw, latitude, longitude))
                .doOnNext(dataResponse -> locationRepository.save(modelMapper.map(dataResponse, Location.class)))
                .onErrorResume(Exception.class, e -> getErrorResponse("response from Google Api did not match expected format: %s", e))
                .onErrorResume(WebClientResponseException.class, e -> getErrorResponse("response from Google API took too long: %s", e))
                .onErrorResume(WebClientException.class, e -> getErrorResponse("no response from the Google API: %s", e));
    }

    private DataResponse extractResponse(GoogleRaw googleRaw, String latitude, String longitude) {
       DataResponse dataResponse = DataResponse.builder()
                .latParam(latitude)
                .lonParam(longitude)
                .dateTimeRequest(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .address(googleRaw.results().get(0).formattedAddress())
                .googleRaw(googleRaw)
                .build();
        return dataResponse;
    }

    private String googleApiKeyValidation(String googleApiKey) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9-_]{39}$");
        if (pattern.matcher(googleApiKey).matches()){
            return googleApiKey;
        }
        throw new RuntimeException("Google API Key is invalid");
    }

    @NotNull
    private static Mono<DataResponse> getErrorResponse(String message, Exception e) {
        log.error(String.format(message, e.getMessage()));
        return Mono.just(DataResponse.builder().error(e.getMessage()).build());
    }
}
