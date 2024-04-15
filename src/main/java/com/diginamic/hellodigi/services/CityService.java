package com.diginamic.hellodigi.services;

import com.diginamic.hellodigi.businessmodel.City;
import com.diginamic.hellodigi.dto.CreateCityRequest;
import com.diginamic.hellodigi.dto.UpdateCityRequest;
import com.diginamic.hellodigi.exceptions.HttpException;

import java.util.List;
import java.util.Optional;

public interface CityService {

  public List<City> addCity(CreateCityRequest request) throws HttpException;

  public List<City> getCities();

  public Optional<City> getCityById(int id);

  public Optional<City> getCityByName(String name);

  public List<City> updateCity(UpdateCityRequest request) throws HttpException;

  public List<City> deleteCity(int id);

  public List<City> findByNameStartingWith(String query);


  public List<City> findByPopulationGreaterThan(int factor);

  public List<City> findByPopulationBetween(int min, int max);

  public List<City> findByDepartmentCodeAndPopulationGreaterThan(String code, int factor);

  public List<City> findByDepartmentCodeAndPopulationBetween(String code, int min, int max);

  public List<City> findByDepartmentCodeOrderByPopulationDesc(String code, int max);
}
