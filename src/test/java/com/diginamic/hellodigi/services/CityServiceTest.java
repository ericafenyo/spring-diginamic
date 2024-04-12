package com.diginamic.hellodigi.services;

import com.diginamic.hellodigi.dto.CreateCityRequest;
import com.diginamic.hellodigi.dto.DepartmentRequest;
import com.diginamic.hellodigi.dto.UpdateCityRequest;
import com.diginamic.hellodigi.exceptions.HttpException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class CityServiceTest {

  @Autowired
  private CityService service;

  CreateCityRequest request = new CreateCityRequest()
      .setName("Nantes")
      .setPopulation(309346)
      .setDepartment(
          new DepartmentRequest()
              .setName("Loire-Atlantique")
              .setCode("44")
      );


  @Test
  void controlTest() {
    Assertions.assertTrue(true);
  }

  @Test
  @DirtiesContext
  void testCreateCity() {
    // Given that the database is empty, When I add a city into the database,
    Assertions.assertDoesNotThrow(() -> service.addCity(request));

    // And I retrieved all cities;
    var cities = service.getCities();

    // Then the result should not be empty.
    Assertions.assertFalse(cities.isEmpty());

    // And a trip with an ID of 1 is present;
    var city = service.getCityById(1);
    Assertions.assertTrue(city.isPresent());
  }

  @Test()
  @DirtiesContext
  void testCityConflict() {
    // Given that the database is empty, When I add a city into the database,
    Assertions.assertDoesNotThrow(() -> service.addCity(request));

    // When I add the same data, it should throw an exception
    Assertions.assertThrows(HttpException.class, () -> service.addCity(request));
  }

  @Test()
  @DirtiesContext
  void testGetCityById() {
    // Given that the database is empty, When I add a city into the database,
    Assertions.assertDoesNotThrow(() -> service.addCity(request));

    // Then a trip with an ID of 1 is present;
    var city = service.getCityById(1);
    Assertions.assertTrue(city.isPresent());
  }

  @Test()
  @DirtiesContext
  void testGetCityByName() {
    // Given that the database is empty, When I add a city into the database,
    Assertions.assertDoesNotThrow(() -> service.addCity(request));

    // Then a trip searched by name of 'Nantes' is present;
    var city = service.getCityByName(request.getName());
    Assertions.assertTrue(city.isPresent());
  }

  @Test()
  @DirtiesContext
  void testUpdateCity() {
    // Given that the database is empty, When I add a city into the database,
    Assertions.assertDoesNotThrow(() -> service.addCity(request));

    // When I get a city by its ID;
    var city = service.getCityById(1);
    Assertions.assertTrue(city.isPresent());

    // And modify the name to 'Paris';
    var newRequest = UpdateCityRequest.from(city.get())
        .setName("Paris");
    // And save the data
    Assertions.assertDoesNotThrow(() -> service.updateCity(newRequest));

    // Then the current city with the same id should have a modified name
    var updated = service.getCityByName("Paris");
    Assertions.assertTrue(updated.isPresent());

    // But should not add to the database
    Assertions.assertEquals(1, service.getCities().size());
  }

  @Test()
  @DirtiesContext
  void testDeleteCity() {
    // Given that the database is empty, When I add a city into the database,
    Assertions.assertDoesNotThrow(() -> service.addCity(request));

    // And I retrieved all cities;
    var cities = service.getCities();

    // Then the result should not be empty.
    Assertions.assertFalse(cities.isEmpty());

    // When I delete the city by id
    service.deleteCity(1);

    // Then the cities are empty
    Assertions.assertTrue(service.getCities().isEmpty());
  }
}
