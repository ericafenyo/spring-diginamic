package com.diginamic.hellodigi.mapper;

import com.diginamic.hellodigi.entities.DepartmentEntity;
import com.diginamic.hellodigi.model.Department;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DepartmentMapper implements Function<DepartmentEntity, Department> {

  @Override
  public Department apply(DepartmentEntity entity) {
    return new Department()
        .setId(entity.getId())
        .setName(entity.getName())
        .setCode(entity.getCode());
  }
}
