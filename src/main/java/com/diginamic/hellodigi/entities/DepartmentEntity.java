package com.diginamic.hellodigi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A database entity representing a department of a country.
 */
@Entity(name = "departments")
@EntityListeners(AuditingEntityListener.class)
public class DepartmentEntity {

  /**
   * The unique identifier of the department.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  /**
   * The name of the department.
   */
  @Column(name = "name", unique = true, length = 50)
  private String name;

  /**
   * The code of the department.
   */
  @Column(name = "code", unique = true, length = 50)
  private String code;

  /**
   * The list of cities in the department.
   */
  @OneToMany(mappedBy = "department")
  private List<CityEntity> cities;

  /**
   * The date and time when the city was created.
   */
//  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  /**
   * The date and time when the city was last updated.
   */
  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public DepartmentEntity() {
  }

  /**
   * Constructs a department with specified id, name, and list of cities.
   *
   * @param id     The unique identifier of the department.
   * @param name   The name of the department.
   * @param cities The list of cities in the department.
   */
  public DepartmentEntity(int id, String name, List<CityEntity> cities) {
    this.id = id;
    this.name = name;
    this.cities = cities;
  }

  public long getId() {
    return id;
  }

  public DepartmentEntity setId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public DepartmentEntity setName(String name) {
    this.name = name;
    return this;
  }

  public List<CityEntity> getCities() {
    return cities;
  }

  public DepartmentEntity setCities(List<CityEntity> cities) {
    this.cities = cities;
    return this;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public DepartmentEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getCode() {
    return code;
  }
}
