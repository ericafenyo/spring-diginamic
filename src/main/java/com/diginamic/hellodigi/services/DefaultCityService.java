package com.diginamic.hellodigi.services;

import com.diginamic.hellodigi.dto.CreateCityRequest;
import com.diginamic.hellodigi.dto.UpdateCityRequest;
import com.diginamic.hellodigi.entities.CityEntity;
import com.diginamic.hellodigi.entities.DepartmentEntity;
import com.diginamic.hellodigi.exceptions.HttpException;
import com.diginamic.hellodigi.mapper.CityMapper;
import com.diginamic.hellodigi.businessmodel.City;
import com.diginamic.hellodigi.repositories.CityRepository;
import com.diginamic.hellodigi.repositories.DepartmentRepository;
import org.springframework.data.domain.Limit;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class DefaultCityService implements CityService {

  private final CityRepository cityRepository;
  private final DepartmentRepository departmentRepository;

  private final CityMapper mapper;

  public DefaultCityService(CityRepository cityRepository, DepartmentRepository departmentRepository, CityMapper mapper) {
    this.cityRepository = cityRepository;
    this.departmentRepository = departmentRepository;
    this.mapper = mapper;
  }

  @Override
  public List<City> addCity(CreateCityRequest request) throws HttpException {

    // Throw an error when the city population is less than 10 inhabitants
    if (request.getPopulation() < 10) {
      throw new HttpException(HttpStatus.BAD_REQUEST, "City population should be at least 10");
    }

    // Throw an error when the city name is less than 2 characters
    if (request.getName().length() < 2) {
      throw new HttpException(HttpStatus.BAD_REQUEST, "City name should be at least 2 characters");
    }

    // Throw an error when the department code is not between 2 and 3 characters both inclusive.
    if (request.getDepartment().getCode().length() < 2 || request.getDepartment().getCode().length() > 3) {
      throw new HttpException(
          HttpStatus.BAD_REQUEST,
          "Department code should be at least 2 and at most 3 characters"
      );
    }

    // Throw an error when the department name is not at least 3 chars.
    if (request.getDepartment().getName().length() < 3) {
      throw new HttpException(
          HttpStatus.BAD_REQUEST,
          "Department name should be least 3 characters"
      );
    }

    var city = cityRepository.findByName(request.getName());

    // Throw an error if city name with the associated department code exists in the database.
    if (city.isPresent()) {
      if (
          city.get().getName().equals(request.getName()) &&
              city.get().getDepartment().getCode().equals(request.getDepartment().getCode())) {
        throw new HttpException(HttpStatus.CONFLICT, "The city name with the provided department code already exist");
      }
    }

    var currentDepartment = departmentRepository.findByName(request.getDepartment().getName());

    DepartmentEntity department = currentDepartment.orElseGet(() -> {
      var departmentEntity = new DepartmentEntity()
          .setName(request.getDepartment().getName())
          .setCode(request.getDepartment().getCode());

      return departmentRepository.save(departmentEntity);
    });

    var entity = new CityEntity()
        .setName(request.getName())
        .setPopulation(request.getPopulation())
        .setDepartment(department);

    cityRepository.save(entity);

    return getCities();
  }

  public List<City> getCities() {
    Iterable<CityEntity> entities = cityRepository.findAll();
    return StreamSupport.stream(entities.spliterator(), false)
        .map(mapper)
        .toList();
  }

  @Override
  public Optional<City> getCityById(int id) {
    return cityRepository.findById(id).map(mapper);
  }

  @Override
  public Optional<City> getCityByName(String name) {
    return cityRepository.findByName(name).map(mapper);
  }

  @Override
  public List<City> updateCity(UpdateCityRequest request) throws HttpException {

    // Throw an error when the department code is not between 2 and 3 characters both inclusive.
    if (request.getDepartment().getCode().length() < 2 || request.getDepartment().getCode().length() > 3) {
      throw new HttpException(
          HttpStatus.BAD_REQUEST,
          "Department code should be at least 2 and at most 3 characters"
      );
    }

    // Throw an error when the department name is not at least 3 chars.
    if (request.getDepartment().getName().length() < 3) {
      throw new HttpException(
          HttpStatus.BAD_REQUEST,
          "Department name should be least 3 characters"
      );
    }


    var entity = cityRepository.findById(request.getId());

    if (entity.isPresent()) {
      var updatedEntity = entity.get()
          .setName(request.getName())
          .setPopulation(request.getPopulation());

      cityRepository.save(updatedEntity);
    }

    return getCities();
  }

  @Override
  public List<City> deleteCity(int id) {
    var entity = cityRepository.findById(id);

    entity.ifPresent(cityRepository::delete);

    return getCities();
  }

  @Override
  public List<City> findByNameStartingWith(String query) {
    Iterable<CityEntity> entities = cityRepository.findByNameStartingWith(query);

    return StreamSupport.stream(entities.spliterator(), false)
        .map(mapper)
        .toList();
  }


  @Override
  public List<City> findByPopulationGreaterThan(int factor) {
    Iterable<CityEntity> entities = cityRepository.findByPopulationGreaterThan(factor);
    return entityListToBusinessModel(entities);
  }

  @Override
  public List<City> findByPopulationBetween(int min, int max) {
    Iterable<CityEntity> entities = cityRepository.findByPopulationBetween(min, max);
    return entityListToBusinessModel(entities);
  }

  @Override
  public List<City> findByDepartmentCodeAndPopulationGreaterThan(String code, int factor) {
    Iterable<CityEntity> entities = cityRepository.findByDepartmentCodeAndPopulationGreaterThan(code, factor);
    return entityListToBusinessModel(entities);
  }

  @Override
  public List<City> findByDepartmentCodeAndPopulationBetween(String code, int min, int max) {
    Iterable<CityEntity> entities = cityRepository.findByDepartmentCodeAndPopulationBetween(code, min, max);
    return entityListToBusinessModel(entities);
  }

  @Override
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
