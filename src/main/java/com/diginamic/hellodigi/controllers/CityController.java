package com.diginamic.hellodigi.controllers;

import com.diginamic.hellodigi.model.City;
import com.diginamic.hellodigi.services.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {
  private final CityService service;

  public CityController(CityService service) {
    this.service = service;
  }

  @PostMapping("/cities")
  public List<City> createCity(@RequestBody City city) {
    return service.addCity(city);
  }

  @PostMapping("/cities/seed")
  public List<City> seedCity(@RequestBody List<City> cities) {
    cities.forEach(service::addCity);
    return getCities();
  }

  @GetMapping("/cities")
  public List<City> getCities() {
    return service.getCities();
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
