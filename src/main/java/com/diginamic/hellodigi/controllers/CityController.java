package com.diginamic.hellodigi.controllers;

import com.diginamic.hellodigi.dto.CreateCityRequest;
import com.diginamic.hellodigi.dto.UpdateCityRequest;
import com.diginamic.hellodigi.exceptions.HttpException;
import com.diginamic.hellodigi.businessmodel.City;
import com.diginamic.hellodigi.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CityController {
  private final CityService service;

  public CityController(CityService service) {
    this.service = service;
  }

  @PostMapping("/cities")
  public List<City> createCity(@RequestBody CreateCityRequest city) throws HttpException {
    return service.addCity(city);
  }

  @PostMapping("/cities/seed")
  public List<City> seedCity(@RequestBody List<CreateCityRequest> cities) throws HttpException {
    for (CreateCityRequest city : cities) {
      service.addCity(city);
    }
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
  public City getCityByName(@PathVariable String name) throws HttpException {
    Optional<City> city = service.getCityByName(name);

    if (city.isEmpty()) {
      throw new HttpException(HttpStatus.NOT_FOUND, "Resource not found");
    }

    return city.get();
  }

  @PutMapping("/cities")
  public List<City> updateCity(@RequestBody UpdateCityRequest city) throws HttpException {
    return service.updateCity(city);
  }

  @DeleteMapping("/cities/{id}")
  public List<City> deleteCity(@PathVariable int id) {
    return service.deleteCity(id);
  }

  @GetMapping("cities/findByNameStartingWith/{keyword}")
  public List<City> findByNameStartingWith(@PathVariable String keyword) throws HttpException {
    var cities = service.findByNameStartingWith(keyword);

    if (cities.isEmpty()) {
      throw new HttpException(HttpStatus.NOT_FOUND,
          "Aucune ville dont le nom commence par '%s' n’a été trouvée".formatted(keyword)
      );
    }

    return cities;
  }

  @GetMapping("cities/findByPopulationGreaterThan/{value}")
  public List<City> findByPopulationGreaterThan(@PathVariable int min) throws HttpException {
    var cities = service.findByPopulationGreaterThan(min);

    if (cities.isEmpty()) {
      throw new HttpException(
          HttpStatus.NOT_FOUND,
          "Aucune ville n’a une population supérieure à %d".formatted(min)
      );
    }

    return cities;
  }

  @GetMapping("cities/findByPopulationBetween/{min}/{max}")
  public List<City> findByPopulationBetween(@PathVariable int min, @PathVariable int max) throws HttpException {
    var cities = service.findByPopulationBetween(min, max);

    if (cities.isEmpty()) {
      throw new HttpException(
          HttpStatus.NOT_FOUND,
          "Aucune ville n’a une population comprise entre %d et %d".formatted(min, max)
      );
    }

    return cities;
  }


  @GetMapping("cities/findByDepartmentCodeAndPopulationGreaterThan/{code}/{min}")
  public List<City> findByDepartmentCodeAndPopulationGreaterThan(
      @PathVariable String code,
      @PathVariable int min
  ) throws HttpException {
    var cities = service.findByDepartmentCodeAndPopulationGreaterThan(code, min);

    if (cities.isEmpty()) {
      throw new HttpException(
          HttpStatus.NOT_FOUND,
          "Aucune ville n’a une population supérieure à %d dans le département %s".formatted(min, code)
      );
    }

    return cities;
  }

  @GetMapping("cities/findByDepartmentCodeAndPopulationBetween/{code}/{min}/{max}")
  public List<City> findByDepartmentCodeAndPopulationBetween(
      @PathVariable String code,
      @PathVariable int min,
      @PathVariable int max
  ) throws HttpException {
    var cities = service.findByDepartmentCodeAndPopulationBetween(code, min, max);

    if (cities.isEmpty()) {
      throw new HttpException(
          HttpStatus.NOT_FOUND,
          "Aucune ville n’a une population comprise entre %d et %d dans le département %s ".formatted(min, max, code)
      );
    }

    return cities;
  }


  @GetMapping("cities/findByDepartmentCodeOrderByPopulationDesc/{code}/{limit}")
  public List<City> findByDepartmentCodeOrderByPopulationDesc(
      @PathVariable String code,
      @PathVariable int limit
  ) {
    return service.findByDepartmentCodeOrderByPopulationDesc(code, limit);
  }
}
