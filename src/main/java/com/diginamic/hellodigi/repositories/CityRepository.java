package com.diginamic.hellodigi.repositories;

import com.diginamic.hellodigi.entities.CityEntity;
import org.springframework.data.domain.Limit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Integer> {
  Optional<CityEntity> findByName(String name);

  Iterable<CityEntity> findByNameStartingWith(String value);

  Iterable<CityEntity> findByPopulationGreaterThan(int factor);

  Iterable<CityEntity> findByPopulationBetween(int min, int max);

  Iterable<CityEntity> findByDepartmentCodeAndPopulationGreaterThan(String code, int population);

  Iterable<CityEntity> findByDepartmentCodeAndPopulationBetween(String code, int min, int max);

  Iterable<CityEntity> findByDepartmentCodeOrderByPopulationDesc(String code, Limit limit);
}
