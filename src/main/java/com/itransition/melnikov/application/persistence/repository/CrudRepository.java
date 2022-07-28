package com.itransition.melnikov.application.persistence.repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository Interface for CRUD operations.
 * @param <T> Object type for performing operations
 * @param <Id> Primary key
 */
public interface CrudRepository<T, Id> {

    /**
     * Creates a new instance of an entity in the database.
     * @param obj Object to create
     * @return optional id of created object
     */
    Optional<Id> create(T obj);

    /**
     * Gets all existing entities with provided type and provided limit and offset.
     * @param limit limit of entities
     * @param offset offset for the entities
     * @return list of entities
     */
    List<T> getAll(int limit, int offset);

    /**
     * Gets entity with the provided id.
     * @param id id of the needed object
     * @return optional object of provided type
     */
    Optional<T> getById(Id id);

    /**
     * Updates provided entity object.
     * @param obj entity with fields that needed to be updated
     * @return id of the object to be updated
     */
    Integer update(T obj);

    /**
     * Deletes entity with the provided id.
     * @param id id of the object to be deleted
     * @return id of the object to be deleted
     */
    Integer delete(Id id);

}
