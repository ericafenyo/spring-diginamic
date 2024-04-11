package com.diginamic.hellodigi.mapper;

import com.diginamic.hellodigi.entities.CityEntity;
import com.diginamic.hellodigi.businessmodel.City;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CityMapper implements Function<CityEntity, City> {

  private final DepartmentMapper mapper;

  public CityMapper(DepartmentMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public City apply(CityEntity entity) {
    return new City()
        .setId(entity.getId())
        .setName(entity.getName())
        .setPopulation(entity.getPopulation())
        .setCreatedAt(entity.getCreatedAt())
        .setUpdatedAt(entity.getUpdatedAt())
        .setDepartment(mapper.apply(entity.getDepartment()));
  }
}
