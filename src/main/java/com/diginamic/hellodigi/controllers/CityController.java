package com.diginamic.hellodigi.controllers;

import com.diginamic.hellodigi.dto.CreateCityRequest;
import com.diginamic.hellodigi.dto.UpdateCityRequest;
import com.diginamic.hellodigi.model.City;
import com.diginamic.hellodigi.services.CityService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {
  private final CityService service;

  public CityController(CityService service) {
    this.service = service;
  }

  @PostMapping("/cities")
  public List<City> createCity(@RequestBody CreateCityRequest city) {
    return service.addCity(city);
  }

  @PostMapping("/cities/seed")
  public List<City> seedCity(@RequestBody List<CreateCityRequest> cities) {
    cities.forEach(service::addCity);
    return getCities();
  }

  @GetMapping("/cities")
  public List<City> getCities() {
    return service.getCities();
  }

  @GetMapping("/cities/{id}")
  public City getCityById(@PathVariable int id) {
    return service.getCityById(id).orElse(null);
  }

  @GetMapping("/cities/findByName{name}")
  public City getCityByName(@PathVariable String name) {
    return service.getCityByName(name).orElse(null);
  }

  @PutMapping("/cities")
  public List<City> updateCity(@RequestBody UpdateCityRequest city) {
    return service.updateCity(city);
  }

  @DeleteMapping("/cities/{id}")
  public List<City> deleteCity(@PathVariable int id) {
    return service.deleteCity(id);
  }

  @GetMapping("cities/findByNameStartingWith/{city}")
  public List<City> findByNameStartingWith(@PathVariable String city) {
    return service.findByNameStartingWith(city);
  }

  @GetMapping("cities/findByPopulationBetween/{min}/{max}")
  public List<City> findByPopulationBetween(@PathVariable int min, @PathVariable int max) {
    return service.findByPopulationBetween(min, max);
  }


  @GetMapping("cities/findByDepartmentCodeAndPopulationGreaterThan/{code}/{population}")
  public List<City> findByDepartmentCodeAndPopulationGreaterThan(
      @PathVariable String code,
      @PathVariable int population
  ) {
    return service.findByDepartmentCodeAndPopulationGreaterThan(code, population);
  }

  @GetMapping("cities/findByDepartmentCodeAndPopulationBetween/{code}/{min}/{max}")
  public List<City> findByDepartmentCodeAndPopulationBetween(
      @PathVariable String code,
      @PathVariable int min,
      @PathVariable int max
  ) {
    return service.findByDepartmentCodeAndPopulationBetween(code, min, max);
  }

  @GetMapping("cities/findByPopulationGreaterThan/{value}")
  public List<City> findByPopulationGreaterThan(@PathVariable int value) {
    return service.findByPopulationGreaterThan(value);
  }

  @GetMapping("cities/findByDepartmentCodeOrderByPopulationDesc/{code}/{limit}")
  public List<City> findByDepartmentCodeOrderByPopulationDesc(
      @PathVariable String code,
      @PathVariable int limit
  ) {
    return service.findByDepartmentCodeOrderByPopulationDesc(code, limit);
  }
}
