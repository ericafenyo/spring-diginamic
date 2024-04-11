package com.diginamic.hellodigi.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents a department of a country.
 */
public class DepartmentRequest {
  /** The name of the department. */
  @NotBlank
  private String name;

  /** The name of the department. */
  @NotBlank
  private String code;

  public String getName() {
    return name;
  }

  public DepartmentRequest setName(String name) {
    this.name = name;
    return this;
  }

  public String getCode() {
    return code;
  }

  public DepartmentRequest setCode(String code) {
    this.code = code;
    return this;
  }
}
