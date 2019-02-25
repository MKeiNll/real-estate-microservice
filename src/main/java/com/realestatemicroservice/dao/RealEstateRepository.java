package com.realestatemicroservice.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.realestatemicroservice.model.RealEstate;

/**
 * 
 * Spring Data JPA repository for the RealEstate entity.
 *
 */
@Repository
public interface RealEstateRepository extends CrudRepository<RealEstate, Long> {
}
