package com.diginamic.hellodigi.services;

import com.diginamic.hellodigi.dto.CreateCityRequest;
import com.diginamic.hellodigi.dto.UpdateCityRequest;
import com.diginamic.hellodigi.entities.CityEntity;
import com.diginamic.hellodigi.entities.DepartmentEntity;
import com.diginamic.hellodigi.mapper.CityMapper;
import com.diginamic.hellodigi.model.City;
import com.diginamic.hellodigi.repositories.CityRepository;
import com.diginamic.hellodigi.repositories.DepartmentRepository;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class CityService {

  private final CityRepository cityRepository;
  private final DepartmentRepository departmentRepository;

  private final CityMapper mapper;

  public CityService(CityRepository cityRepository, DepartmentRepository departmentRepository, CityMapper mapper) {
    this.cityRepository = cityRepository;
    this.departmentRepository = departmentRepository;
    this.mapper = mapper;
  }

  public List<City> addCity(CreateCityRequest city) {
    var currentCity = cityRepository.findByName(city.getName());

    if (currentCity.isEmpty()){
      var departmentEntity = new DepartmentEntity()
          .setName(city.getDepartment().getName())
          .setCode(city.getDepartment().getCode());

      var currentDepartment = departmentRepository.findByName(city.getDepartment().getName());

      DepartmentEntity department = currentDepartment.orElseGet(() -> departmentRepository.save(departmentEntity));

      var entity = new CityEntity()
          .setName(city.getName())
          .setPopulation(city.getPopulation())
          .setDepartment(department);

      cityRepository.save(entity);
    }

    return getCities();
  }

  public List<City> getCities() {
    Iterable<CityEntity> entities = cityRepository.findAll();
    return StreamSupport.stream(entities.spliterator(), false)
        .map(mapper)
        .toList();
  }

  public Optional<City> getCityById(int id) {
    return cityRepository.findById(id).map(mapper);
  }

  public Optional<City> getCityByName(String name) {
    return cityRepository.findByName(name).map(mapper);
  }

  public List<City> updateCity(UpdateCityRequest city) {
    var entity = cityRepository.findById(city.getId());

    if (entity.isPresent()) {
      var updatedEntity = entity.get()
          .setName(city.getName())
          .setPopulation(city.getPopulation());

      cityRepository.save(updatedEntity);
    }

    return getCities();
  }

  public List<City> deleteCity(int id) {
    var entity = cityRepository.findById(id);

    entity.ifPresent(cityRepository::delete);

    return getCities();
  }

  public List<City> findByNameStartingWith(String query) {
    Iterable<CityEntity> entities = cityRepository.findByNameStartingWith(query);

    return StreamSupport.stream(entities.spliterator(), false)
        .map(mapper)
        .toList();
  }


  public List<City> findByPopulationGreaterThan(int factor) {
    Iterable<CityEntity> entities = cityRepository.findByPopulationGreaterThan(factor);
    return entityListToBusinessModel(entities);
  }

  public List<City> findByPopulationBetween(int min, int max) {
    Iterable<CityEntity> entities = cityRepository.findByPopulationBetween(min, max);
    return entityListToBusinessModel(entities);
  }

  public List<City> findByDepartmentCodeAndPopulationGreaterThan(String code, int factor) {
    Iterable<CityEntity> entities = cityRepository.findByDepartmentCodeAndPopulationGreaterThan(code, factor);
    return entityListToBusinessModel(entities);
  }

  public List<City> findByDepartmentCodeAndPopulationBetween(String code, int min, int max) {
    Iterable<CityEntity> entities = cityRepository.findByDepartmentCodeAndPopulationBetween(code, min, max);
    return entityListToBusinessModel(entities);
  }

  public List<City> findByDepartmentCodeOrderByPopulationDesc(String code, int max) {
    Iterable<CityEntity> entities = cityRepository.findByDepartmentCodeOrderByPopulationDesc(code, Limit.of(max));
    return entityListToBusinessModel(entities);
  }

  private List<City> entityListToBusinessModel(Iterable<CityEntity> entities) {
    return StreamSupport.stream(entities.spliterator(), false)
        .map(mapper)
        .toList();
  }
}
