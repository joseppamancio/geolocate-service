package com.geolocate.domain.repository;

import com.geolocate.domain.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LocationRepository extends MongoRepository<Location, String> {
}