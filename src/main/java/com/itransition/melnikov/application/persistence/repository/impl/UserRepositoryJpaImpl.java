package com.itransition.melnikov.application.persistence.repository.impl;

import com.itransition.melnikov.application.persistence.entity.user.UserEntity;
import com.itransition.melnikov.application.persistence.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryJpaImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Integer> create(UserEntity user) {
        entityManager.persist(user);
        Integer id = user.getId();
        return id != 0
                ? Optional.of(id)
                : Optional.empty();
    }

    @Override
    public List<UserEntity> getAll(int limit, int offset) {
        return entityManager
                .createQuery("SELECT u FROM UserEntity u", UserEntity.class)
                .setMaxResults(limit)
                .setFirstResult(offset)
                .getResultList();
    }

    @Override
    public Optional<UserEntity> getById(Integer id) {
        List<UserEntity> users =  entityManager
                .createQuery("SELECT u FROM UserEntity u WHERE u.id = :id", UserEntity.class)
                .setParameter("id", id)
                .getResultList();
        return users.isEmpty()
                ? Optional.empty()
                : Optional.of(users.get(0));
    }

    @Override
    public Optional<UserEntity> getByUsername(String username) {
        List<UserEntity> users =  entityManager
                .createQuery("SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class)
                .setParameter("username", username)
                .getResultList();
        return users.isEmpty()
                ? Optional.empty()
                : Optional.of(users.get(0));
    }

    @Override
    public Integer update(UserEntity user) {
        return entityManager.merge(user).getId();
    }

    @Override
    public Integer delete(Integer id) {
        return entityManager
                .createQuery("DELETE FROM UserEntity WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public boolean isExistByUsername(String username) {
        List<UserEntity> users = entityManager
                .createQuery("SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class)
                .setParameter("username", username)
                .getResultList();
        return !users.isEmpty();
    }

    @Override
    public Integer getCount() {
        return entityManager
                .createQuery("SELECT COUNT(c) FROM UserEntity c", Integer.class)
                .getSingleResult();
    }
}