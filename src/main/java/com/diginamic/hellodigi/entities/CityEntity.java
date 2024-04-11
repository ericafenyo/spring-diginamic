package com.diginamic.hellodigi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * A database entity representing a city of a country.
 */
@Entity(name = "cities")
@EntityListeners(AuditingEntityListener.class)
public class CityEntity {
  /**
   * The unique identifier of the city.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  /**
   * The name of the city.
   */
  @Column(name = "name", length = 50)
  private String name;

  /**
   * The population of the city.
   */
  @Column(name = "population")
  private long population;

  @ManyToOne
  @JoinColumn(name = "department_id", referencedColumnName = "id")
  private DepartmentEntity department;

  /**
   * The date and time when the city was created.
   */
  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  /**
   * The date and time when the city was last updated.
   */
  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  // No Arg constructor required
  public CityEntity() {
  }

  /**
   * Constructs a city with specified id, name, and population.
   *
   * @param id         The unique identifier of the city.
   * @param name       The name of the city.
   * @param population The population of the city.
   */
  public CityEntity(int id, String name, long population) {
    this.id = id;
    this.name = name;
    this.population = population;
  }


  public int getId() {
    return id;
  }

  public CityEntity setId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public CityEntity setName(String name) {
    this.name = name;
    return this;
  }

  public long getPopulation() {
    return population;
  }

  public CityEntity setDepartment(DepartmentEntity department) {
    this.department = department;
    return this;
  }

  public DepartmentEntity getDepartment() {
    return department;
  }

  public CityEntity setPopulation(long population) {
    this.population = population;
    return this;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
