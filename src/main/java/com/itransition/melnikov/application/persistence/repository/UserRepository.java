package com.itransition.melnikov.application.persistence.repository;

import com.itransition.melnikov.application.persistence.entity.user.UserEntity;

import java.util.Optional;

/**
 * Extends {@link CrudRepository} interface for CRUD operations for {@link UserEntity} entity.
 */
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    /**
     * Checks whether user exists by user name
     * @param username user's username
     * @return true of false
     */
    boolean isExistByUsername(String username);

    /**
     *
     * @param username
     * @return
     */
    Optional<UserEntity> getByUsername(String username);

    /**
     *
     * @return
     */
    Integer getCount();

}

