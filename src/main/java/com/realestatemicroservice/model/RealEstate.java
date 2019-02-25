package com.realestatemicroservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

/**
 * 
 * A real estate object.
 *
 */
@Entity
@Table(name = "real_estate")
@Validated
public class RealEstate {

	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "USER_ID")
	@Min(0)
	private Long userId;
	@Column(name = "STREET_NAME")
	@Length(min = 1)
	private String streetName;
	@Column(name = "HOUSE_NUMBER")
	@Length(min = 1)
	private String houseNumber;
	@Column(name = "APARTMENT_NUMBER")
	@Min(1)
	private Integer apartmentNumber;
	@Column(name = "CITY")
	@Length(min = 1)
	private String city;
	@Column(name = "COUNTRY")
	@Length(min = 1)
	private String country;
	@Column(name = "CONSTRUCTION_YEAR")
	@Digits(integer = 4, fraction = 0)
	private Integer constructionYear;
	@Column(name = "TOTAL_AREA_M2")
	@Min(1)
	private Double totalAreaM2;

	public RealEstate(Long userId, String streetName, String houseNumber, Integer apartmentNumber, String city,
			String country, Integer constructionYear, Double totalAreaM2) {
		this.userId = userId;
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.apartmentNumber = apartmentNumber;
		this.city = city;
		this.country = country;
		this.constructionYear = constructionYear;
		this.totalAreaM2 = totalAreaM2;
	}

	private RealEstate(Builder builder) {
		userId = builder.userId;
		streetName = builder.streetName;
		houseNumber = builder.houseNumber;
		apartmentNumber = builder.apartmentNumber;
		city = builder.city;
		country = builder.country;
		constructionYear = builder.constructionYear;
		totalAreaM2 = builder.totalAreaM2;
	}

	public RealEstate() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public Integer getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(Integer apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getConstructionYear() {
		return constructionYear;
	}

	public void setConstructionYear(Integer constructionYear) {
		this.constructionYear = constructionYear;
	}

	public Double getTotalAreaM2() {
		return totalAreaM2;
	}

	public void setTotalAreaM2(Double totalAreaM2) {
		this.totalAreaM2 = totalAreaM2;
	}

	@Override
	public String toString() {
		return "ID: " + id + "\nUserID: " + userId + "\nStreet Name: " + streetName + "\nHouse Number: " + houseNumber
				+ "\nApartment Number: " + apartmentNumber + "\nCity: " + city + "\nCountry: " + country
				+ "\nConstruction Year: " + constructionYear + "\nTotal Area (m^2): " + totalAreaM2;
	}

	public static class Builder {

		// Required parameters
		private Long userId;
		private String streetName;
		private String houseNumber;

		// Optional parameters
		private Integer apartmentNumber;
		private String city;
		private String country;
		private Integer constructionYear;
		private Double totalAreaM2;

		public Builder(Long userId, String streetName, String houseNumber) {
			this.userId = userId;
			this.streetName = streetName;
			this.houseNumber = houseNumber;

			System.out.println("11111111111111111111111111111111111: " + this.userId);
		}

		public Builder apartmentNumber(Integer apartmentNumber) {
			this.apartmentNumber = apartmentNumber;
			return this;
		}

		public Builder city(String city) {
			this.city = city;
			return this;
		}

		public Builder country(String country) {
			this.country = country;
			return this;
		}

		public Builder constructionYear(Integer constructionYear) {
			this.constructionYear = constructionYear;
			return this;
		}

		public Builder totalAreaM2(Double totalAreaM2) {
			this.totalAreaM2 = totalAreaM2;
			return this;
		}

		public RealEstate build() {
			return new RealEstate(this);
		}
	}
}