package com.realestatemicroservice.service;

import com.realestatemicroservice.dao.RealEstateRepository;
import com.realestatemicroservice.exceptions.NoFieldsToEditSpecifiedException;
import com.realestatemicroservice.exceptions.RealEstateObjectDoesNotExistException;
import com.realestatemicroservice.model.RealEstate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RealEstateServiceImpl implements RealEstateService {

    public static final String OBJECT_CREATED_MESSAGE = "Real estate object successfully created.";
    public static final String OBJECT_EDITED_MESSAGE = "Real estate object successfully edited.";
    public static final String OBJECT_DELETED_MESSAGE = "Real estate object successfully deleted.";

    @Autowired
    private RealEstateRepository realEstateRepository;

    @Override
    public List<RealEstate> getRealEstateObjects() {
        List<RealEstate> realEstateList = new ArrayList<RealEstate>();
        realEstateRepository.findAll().forEach(realEstateList::add);
        return realEstateList;
    }

    @Override
    public ResponseEntity<String> createRealEstateObject(Long userId, String streetName, String houseNumber,
                                                         Integer apartmentNumber, String city, String country,
                                                         Integer constructionYear, Double totalAreaM2) {
        realEstateRepository.save(new RealEstate(userId, streetName, houseNumber, apartmentNumber, city, country,
                constructionYear, totalAreaM2));
        return new ResponseEntity<String>(OBJECT_CREATED_MESSAGE, HttpStatus.CREATED);
    }

    @Override
    public RealEstate getRealEstateObject(Long id) {
        if (id != null && realEstateRepository.existsById(id)) {
            return realEstateRepository.findById(id).get();
        }
        throw new RealEstateObjectDoesNotExistException();
    }

    @Override
    public ResponseEntity<String> updateRealEstateObject(Long id, Long userId, String streetName, String houseNumber,
                                                         Integer apartmentNumber, String city, String country,
                                                         Integer constructionYear, Double totalAreaM2) {
        if (id != null && realEstateRepository.existsById(id)) {
            if (userId == null && streetName == null && houseNumber == null && apartmentNumber == null && city == null
                    && country == null && constructionYear == null && totalAreaM2 == null) {
                throw new NoFieldsToEditSpecifiedException();
            } else {
                RealEstate realEstate = realEstateRepository.findById(id).get();
                if (userId != null)
                    realEstate.setUserId(userId);
                if (streetName != null)
                    realEstate.setStreetName(streetName);
                if (houseNumber != null)
                    realEstate.setHouseNumber(houseNumber);
                if (apartmentNumber != null)
                    realEstate.setApartmentNumber(apartmentNumber);
                if (city != null)
                    realEstate.setCity(city);
                if (country != null)
                    realEstate.setCountry(country);
                if (constructionYear != null)
                    realEstate.setConstructionYear(constructionYear);
                if (totalAreaM2 != null)
                    realEstate.setTotalAreaM2(totalAreaM2);
                realEstateRepository.save(realEstate);
                return new ResponseEntity<String>(OBJECT_EDITED_MESSAGE, HttpStatus.OK);
            }
        }
        throw new RealEstateObjectDoesNotExistException();
    }

    @Override
    public ResponseEntity<String> deleteRealEstateObject(Long id) {
        if (id != null && realEstateRepository.existsById(id)) {
            realEstateRepository.deleteById(id);
            return new ResponseEntity<String>(OBJECT_DELETED_MESSAGE, HttpStatus.OK);
        }
        throw new RealEstateObjectDoesNotExistException();
    }
}
