package com.realestatemicroservice.controller;

import com.realestatemicroservice.exceptions.NoFieldsToEditSpecifiedException;
import com.realestatemicroservice.exceptions.RealEstateObjectDoesNotExistException;
import com.realestatemicroservice.model.RealEstate;
import com.realestatemicroservice.service.RealEstateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for managing real estate objects. This class accesses the
 * RealEstate entity.
 */
@RestController
@RequestMapping("/realestate")
public class RealEstateController {

    @Autowired
    private RealEstateServiceImpl realEstateService;

    /**
     * POST /realestate : Lists all real estate objects.
     *
     * @return body with all real estate objects.
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public List<RealEstate> getAllRealEstateObjects() {
        return realEstateService.getRealEstateObjects();
    }

    /**
     * PUT /realestate : Creates a new real estate object.
     * <p>
     * Creates a new real estate object if all required parameters are provided &
     * valid.
     *
     * @param userId           the user ID. Required.
     * @param streetName       the street name. Required.
     * @param houseNumber      the house number. Required.
     * @param apartmentNumber  the apartment number. Optional.
     * @param city             the city. Optional.
     * @param country          the country. Optional.
     * @param constructionYear the construction year. Optional.
     * @param totalAreaM2      the total area in m^2. Optional.
     * @return the ResponseEntity with status 201 (Created) and with body message
     * {@link RealEstateServiceImpl#OBJECT_CREATED_MESSAGE}
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> createNewRealEstateObject(
            @RequestParam(value = "userId", required = true) @Valid Long userId,
            @RequestParam(value = "streetName", required = true) @Valid String streetName,
            @RequestParam(value = "houseNumber", required = true) @Valid String houseNumber,
            @RequestParam(value = "apartmentNumber", required = false) @Valid Integer apartmentNumber,
            @RequestParam(value = "city", required = false) @Valid String city,
            @RequestParam(value = "country", required = false) @Valid String country,
            @RequestParam(value = "constructionYear", required = false) @Valid Integer constructionYear,
            @RequestParam(value = "totalAreaM2", required = false) @Valid Double totalAreaM2) {
        return realEstateService.createRealEstateObject(userId, streetName, houseNumber, apartmentNumber, city, country,
                constructionYear, totalAreaM2);
    }

    /**
     * POST /realestate/:id : Retrieves the real estate object by it's id.
     *
     * @param id the id of the real estate object to find.
     * @return the real estate object.
     * @throws RealEstateObjectDoesNotExistException if the requested object does
     *                                               not exist.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RealEstate getRealEstateObjectById(@PathVariable Long id) {
        return realEstateService.getRealEstateObject(id);
    }

    /**
     * PUT /realestate/:id : Updates an existing real estate object by it's id.
     * <p>
     * Updates an existing real estate object if at least one field to edit is
     * provided & valid.
     *
     * @param id               the id of the real estate object to edit.
     * @param userId           the user ID. Optional.
     * @param streetName       the street name. Optional.
     * @param houseNumber      the house number. Optional.
     * @param apartmentNumber  the apartment number. Optional.
     * @param city             the city. Optional.
     * @param country          the country. Optional.
     * @param constructionYear the construction year. Optional.
     * @param totalAreaM2      the total area in m^2. Optional.
     * @return the ResponseEntity with status 200 (OK) and with body message
     * {@link RealEstateServiceImpl#OBJECT_EDITED_MESSAGE}
     * @throws RealEstateObjectDoesNotExistException if the requested object does
     *                                               not exist.
     * @throws NoFieldsToEditSpecifiedException      if the zero fields to edit are
     *                                               specified.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> editRealEstateObjectById(@PathVariable Long id,
                                                           @RequestParam(value = "userId", required = false) @Valid Long userId,
                                                           @RequestParam(value = "streetName", required = false) @Valid String streetName,
                                                           @RequestParam(value = "houseNumber", required = false) @Valid String houseNumber,
                                                           @RequestParam(value = "apartmentNumber", required = false) @Valid Integer apartmentNumber,
                                                           @RequestParam(value = "city", required = false) @Valid String city,
                                                           @RequestParam(value = "country", required = false) @Valid String country,
                                                           @RequestParam(value = "constructionYear",
                                                                   required = false) @Valid Integer constructionYear,
                                                           @RequestParam(value = "totalAreaM2", required = false) @Valid Double totalAreaM2) {
        return realEstateService.updateRealEstateObject(id, userId, streetName, houseNumber, apartmentNumber, city,
                country,
                constructionYear, totalAreaM2);
    }

    /**
     * DELETE /realestate/:id : deletes the real estate object by it's id.
     *
     * @param id the id of the real estate object to delete.
     * @return the ResponseEntity with status 200 (OK) and with body message
     * {@link RealEstateServiceImpl#OBJECT_DELETED_MESSAGE}
     * @throws RealEstateObjectDoesNotExistException if the requested object does
     *                                               not exist.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteRealEstateObjectById(@PathVariable Long id) {
        return realEstateService.deleteRealEstateObject(id);
    }
}