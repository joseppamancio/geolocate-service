package com.geolocate.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Document(collection = "locations")
public class Location {

    @Id
    private String id;

    @NotBlank
    private String latParam;

    @NotBlank
    private String lonParam;

    @NotBlank
    private String dtmRequest;

    @NotBlank
    private String address;

    @NotNull
    private String googleRaw;

}