package com.realestatemicroservice.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.realestatemicroservice.controller.RealEstateController;
import com.realestatemicroservice.exceptions.RealEstateExceptionHandler;
import com.realestatemicroservice.model.RealEstate;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class RealEstateControllerTest {

	private RealEstate dummyRealEstate1;
	private RealEstate dummyRealEstate2;
	private MockMvc mockMvc;

	@MockBean
	private RealEstateController realEstateController;

	@Before
	public void setup() {
		dummyRealEstate1 = new RealEstate.Builder(Long.valueOf(5), "Kai", "6").apartmentNumber(Integer.valueOf(24))
				.city("Narva").country("Estonia").constructionYear(Integer.valueOf(1946))
				.totalAreaM2(Double.valueOf(230)).build();
		dummyRealEstate2 = new RealEstate.Builder(Long.valueOf(6), "Lai", "7").apartmentNumber(Integer.valueOf(25))
				.city("Tallinn").country("Russia").constructionYear(Integer.valueOf(1947))
				.totalAreaM2(Double.valueOf(235)).build();

		Mockito.reset(realEstateController);
		mockMvc = MockMvcBuilders.standaloneSetup(realEstateController)
				.setControllerAdvice(new RealEstateExceptionHandler()).build();
	}

	@Test
	public void getAllRealEstateObjects_RealEstateObjectsFound_ShouldReturnFoundRealEstateObjects() throws Exception {
		when(realEstateController.getAllRealEstateObjects())
				.thenReturn(Arrays.asList(dummyRealEstate1, dummyRealEstate2));

		mockMvc.perform(post("/realestate")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].userId", is(dummyRealEstate1.getUserId().intValue())))
				.andExpect(jsonPath("$[0].streetName", is(dummyRealEstate1.getStreetName())))
				.andExpect(jsonPath("$[0].houseNumber", is(dummyRealEstate1.getHouseNumber())))
				.andExpect(jsonPath("$[0].apartmentNumber", is(dummyRealEstate1.getApartmentNumber())))
				.andExpect(jsonPath("$[0].city", is(dummyRealEstate1.getCity())))
				.andExpect(jsonPath("$[0].country", is(dummyRealEstate1.getCountry())))
				.andExpect(jsonPath("$[0].constructionYear", is(dummyRealEstate1.getConstructionYear())))
				.andExpect(jsonPath("$[0].totalAreaM2", is(dummyRealEstate1.getTotalAreaM2())))
				.andExpect(jsonPath("$[1].userId", is(dummyRealEstate2.getUserId().intValue())))
				.andExpect(jsonPath("$[1].streetName", is(dummyRealEstate2.getStreetName())))
				.andExpect(jsonPath("$[1].houseNumber", is(dummyRealEstate2.getHouseNumber())))
				.andExpect(jsonPath("$[1].apartmentNumber", is(dummyRealEstate2.getApartmentNumber())))
				.andExpect(jsonPath("$[1].city", is(dummyRealEstate2.getCity())))
				.andExpect(jsonPath("$[1].country", is(dummyRealEstate2.getCountry())))
				.andExpect(jsonPath("$[1].constructionYear", is(dummyRealEstate2.getConstructionYear())))
				.andExpect(jsonPath("$[1].totalAreaM2", is(dummyRealEstate2.getTotalAreaM2())));
	}

	@Test
	public void createNewRealEstateObject_fieldsAreValid_ShouldReturnValidResponse() throws Exception {
		when(realEstateController.createNewRealEstateObject(dummyRealEstate1.getUserId(),
				dummyRealEstate1.getStreetName(), dummyRealEstate1.getHouseNumber(),
				dummyRealEstate1.getApartmentNumber(), dummyRealEstate1.getCity(), dummyRealEstate1.getCountry(),
				dummyRealEstate1.getConstructionYear(), dummyRealEstate1.getTotalAreaM2())).thenReturn(
						new ResponseEntity<String>(RealEstateController.OBJECT_CREATED_MESSAGE, HttpStatus.CREATED));

		mockMvc.perform(put("/realestate/?userId=" + dummyRealEstate1.getUserId().toString() + "&streetName="
				+ dummyRealEstate1.getStreetName() + "&houseNumber=" + dummyRealEstate1.getHouseNumber()
				+ "&apartmentNumber=" + dummyRealEstate1.getApartmentNumber().toString() + "&city="
				+ dummyRealEstate1.getCity() + "&country=" + dummyRealEstate1.getCountry() + "&constructionYear="
				+ dummyRealEstate1.getConstructionYear().toString() + "&totalAreaM2="
				+ dummyRealEstate1.getTotalAreaM2().toString())).andExpect(status().isCreated())
				.andExpect(content().string(RealEstateController.OBJECT_CREATED_MESSAGE));
	}
}
