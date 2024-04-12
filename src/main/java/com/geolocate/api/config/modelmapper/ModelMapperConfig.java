package com.geolocate.api.config.modelmapper;

import com.geolocate.domain.model.Location;
import com.geolocate.service.model.DataResponse;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<DataResponse, Location> dataResponseToLocationMap = new PropertyMap<>() {
            protected void configure() {
                map().setLatParam(source.getLatParam());
                map().setLonParam(source.getLonParam());
                map().setDtmRequest(source.dateTimeRequest());
                map().setAddress(source.getAddress());
                using(ctx -> new Gson().toJson(ctx.getSource())).map(source.getGoogleRaw(), destination.getGoogleRaw());
            }
        };

        modelMapper.addMappings(dataResponseToLocationMap);

        return modelMapper;
    }
}