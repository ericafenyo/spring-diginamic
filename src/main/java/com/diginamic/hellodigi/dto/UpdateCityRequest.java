package com.diginamic.hellodigi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a city of a country.
 */
public class UpdateCityRequest {
  /**
   * The unique identifier of the city.
   */
  private int id;

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

  public int getId() {
    return id;
  }

  public UpdateCityRequest setId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public UpdateCityRequest setName(String name) {
    this.name = name;
    return this;
  }

  public long getPopulation() {
    return population;
  }

  public UpdateCityRequest setPopulation(long population) {
    this.population = population;
    return this;
  }

  public DepartmentRequest getDepartment() {
    return department;
  }

  public UpdateCityRequest setDepartment(DepartmentRequest department) {
    this.department = department;
    return this;
  }
}
