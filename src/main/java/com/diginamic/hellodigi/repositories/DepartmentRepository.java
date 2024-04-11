package com.diginamic.hellodigi.repositories;

import com.diginamic.hellodigi.entities.CityEntity;
import com.diginamic.hellodigi.entities.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Integer> {
  Optional<DepartmentEntity> findByName(String name);
}
