package com.diginamic.hellodigi.services;

import com.diginamic.hellodigi.data.Cities;
import com.diginamic.hellodigi.entities.CityEntity;
import com.diginamic.hellodigi.entities.DepartmentEntity;
import com.diginamic.hellodigi.repositories.CityRepository;
import com.diginamic.hellodigi.repositories.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class Seeder implements CommandLineRunner {

  private final CityRepository cityRepository;
  private final DepartmentRepository departmentRepository;

  public Seeder(CityRepository cityRepository, DepartmentRepository departmentRepository) {
    this.cityRepository = cityRepository;
    this.departmentRepository = departmentRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    var cities = Cities.create();
    cities.forEach(city -> {
      var currentDepartment = departmentRepository.findByName(city.departmentName());

      DepartmentEntity departmentEntity = currentDepartment.orElseGet(() -> departmentRepository.save(
          new DepartmentEntity()
              .setName(city.departmentName())
              .setCode(city.departmentCode())
      ));

      var entity = new CityEntity()
          .setName(city.cityName())
          .setPopulation(0)
          .setDepartment(departmentEntity);

      cityRepository.save(entity);
    });

    System.out.println("Finish");
  }


}
