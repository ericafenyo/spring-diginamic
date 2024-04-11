package com.diginamic.hellodigi.model;

/**
 * Represents a city of a country.
 */
public class City {
  /**
   * The unique identifier of the city.
   */
  private int id;
  /**
   * The name of the city.
   */
  private String name;
  /**
   * The population of the city.
   */
  private long population;
  /**
   * The department of a city.
   */
  private Department department;

  // No args constructor. Do not delete
  public City() {}

  /**
   * Constructs a city with specified id, name, and population.
   *
   * @param id         The unique identifier of the city.
   * @param name       The name of the city.
   * @param population The population of the city.
   */
  public City(int id, String name, long population) {
    this.id = id;
    this.name = name;
    this.population = population;
  }

  public int getId() {
    return id;
  }

  public City setId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public City setName(String name) {
    this.name = name;
    return this;
  }

  public long getPopulation() {
    return population;
  }

  public City setPopulation(long population) {
    this.population = population;
    return this;
  }

  public City setDepartment(Department department) {
    this.department = department;
    return this;
  }

  public Department getDepartment() {
    return department;
  }
}
