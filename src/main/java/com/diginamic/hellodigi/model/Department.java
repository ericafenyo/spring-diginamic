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

  public Department() {}

  /**
   * Constructs a department with specified id, name, and list of cities.
   * @param id The unique identifier of the department.
   * @param name The name of the department.
   */
  public Department(long id, String name, String code) {
    this.id = id;
    this.name = name;
    this.code = code;
  }

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
}
