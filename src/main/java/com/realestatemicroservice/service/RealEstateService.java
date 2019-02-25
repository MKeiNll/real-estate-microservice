package com.realestatemicroservice.service;

import com.realestatemicroservice.model.RealEstate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RealEstateService {

    List<RealEstate> getRealEstateObjects();

    ResponseEntity<String> createRealEstateObject(Long userId, String streetName, String houseNumber,
                                                  Integer apartmentNumber, String city, String country,
                                                  Integer constructionYear, Double totalAreaM2);

    RealEstate getRealEstateObject(Long id);

    ResponseEntity<String> updateRealEstateObject(Long id, Long userId, String streetName, String houseNumber,
                                                  Integer apartmentNumber, String city, String country,
                                                  Integer constructionYear, Double totalAreaM2);

    ResponseEntity<String> deleteRealEstateObject(Long id);
}
