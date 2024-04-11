package com.diginamic.hellodigi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a city of a country.
 */
public class CreateCityRequest {
  /**
   * The name of the city.
   */
  @NotBlank
  private String name;
  /**
   * The population of the city.
   */
  @Min(1)
  private long population;
  /**
   * The department of a city.
   */
  @Valid
  private DepartmentRequest department;

  public String getName() {
    return name;
  }

  public CreateCityRequest setName(String name) {
    this.name = name;
    return this;
  }

  public long getPopulation() {
    return population;
  }

  public CreateCityRequest setPopulation(long population) {
    this.population = population;
    return this;
  }

  public DepartmentRequest getDepartment() {
    return department;
  }

  public CreateCityRequest setDepartment(DepartmentRequest department) {
    this.department = department;
    return this;
  }
}
