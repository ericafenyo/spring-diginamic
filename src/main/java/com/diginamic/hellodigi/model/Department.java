package com.diginamic.hellodigi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a department of a country.
 */
public class Department {
  /** The unique identifier of the department. */
  private long id;
  /** The name of the department. */
  private String name;

  /** The name of the department. */
  private String code;

  /** The name of the department. */
  private String population;

  public Department() {}

  public long getId() {
    return id;
  }

  public Department setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Department setName(String name) {
    this.name = name;
    return this;
  }

  public String getCode() {
    return code;
  }

  public Department setCode(String code) {
    this.code = code;
    return this;
  }

  public String getPopulation() {
    return population;
  }

  public Department setPopulation(String population) {
    this.population = population;
    return this;
  }
}
