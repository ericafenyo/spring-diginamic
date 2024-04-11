package com.diginamic.hellodigi.dao;

import com.diginamic.hellodigi.entities.CityEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * An object for managing city database data transactions.
 * <p>
 * This could have been an interface to provide a flexibility
 * to switch different implementations and data sources(databases)
 * <p>
 * Transactional annotation is added to methods that produce side effects
 * on the database.
 * This will revert all changes to the data in the database
 * during an error.
 * <p>
 * Exception handling is also ignored for the moment.
 */
//@Repository TODO: Uncomment to use this
public class CityDao {

  // The implementation is automagically injected.
  @PersistenceContext
  private EntityManager manager;

  /**
   * Find and return a list of city entities in the database.
   * The result is wrapped in an {@link Optional} object for safe api calls.
   */
  public Iterable<CityEntity> find() {
    return manager.createQuery("SELECT e FROM cities as e", CityEntity.class).getResultList();
  }

  /**
   * Find and return a city by its id.
   * The result is wrapped in an {@link Optional} object for safe api calls.
   *
   * @param id the id for the city.
   */
  public Optional<CityEntity> findById(int id) {
    return manager.createQuery("SELECT e FROM cities as e WHERE e.id=:id", CityEntity.class)
        .setParameter("id", id)
        .getResultStream()
        .findFirst();
  }

  /**
   * Find and return a city by its name.
   * The result is wrapped in an {@link Optional} object for safe api calls.
   *
   * @param name the name for the city.
   */
  public Optional<CityEntity> findByName(String name) {
    return manager.createQuery("SELECT e FROM cities as e WHERE e.name=:name", CityEntity.class)
        .setParameter("name", name)
        .getResultStream()
        .findFirst();
  }

  /**
   * Insert a city entity into the database.
   * Any conflict will be ignored.
   *
   * @param entity the entity to be inserted
   * @return the current list of the city in the database after the transaction.
   */
  @Transactional
  public Iterable<CityEntity> save(CityEntity entity) {
    manager.persist(entity);
    return find();
  }

  /**
   * Modify an entity in the database using information from the given entity property.
   *
   * @param entity an object containg the updated city information
   * @return the current list of the city in the database after the transaction.
   */
  @Transactional
  public Iterable<CityEntity> update(CityEntity entity) {
    manager.merge(entity);
    return find();
  }

  /**
   * Remove a city from the database using the provided id.
   *
   * @param id the id for the city to be removed from the database.
   * @return the current list of the city in the database after the transaction.
   */
  @Transactional
  public Iterable<CityEntity> delete(int id) {
    Optional<CityEntity> city = findById(id);
    city.ifPresent(entity -> manager.remove(entity));
    return find();
  }

  /**
   * Return true if an entity with the given id exists in the database.
   *
   * @param id the id of the city
   */
  public boolean existById(int id) {
    return manager.createQuery("SELECT COUNT(e) FROM CityEntity e WHERE e.id = :id", Long.class)
        .setParameter("id", id)
        .getSingleResult() == 0;
  }

  /**
   * Return true if an entity with the given name exists in the database.
   *
   * @param name the name of the city
   */
  public boolean existByName(int name) {
    return manager.createQuery("SELECT COUNT(e) FROM CityEntity e WHERE e.name = :name", Long.class)
        .setParameter("name", name)
        .getSingleResult() == 0;
  }
}
