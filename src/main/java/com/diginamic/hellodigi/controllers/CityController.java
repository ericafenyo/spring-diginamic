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
}
