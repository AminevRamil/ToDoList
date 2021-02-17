package com.epam.starbun.todolist.repository;

import com.epam.starbun.todolist.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepository implements CommonRepository<User>{

    private final EntityManager entityManager;

    @Override
    public Optional<User> findById(UUID id) {
        log.debug("Finding User entity by id={}", id);
        try {
            return Optional.ofNullable(entityManager.find(User.class, id));
        } catch (PersistenceException e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        try {
            log.debug("Finding all User entities in repository");
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> c = query.from(User.class);
            query.select(c);
            List<User> resultList = entityManager.createQuery(query).getResultList();
            return resultList;
        } catch (PersistenceException e) {
            log.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public User save(User user) {
        try {
            entityManager.getTransaction().begin();
            if (entityManager.find(User.class, user.getId()) != null) {
                log.debug("Updating entity in repository. Updated User: {}", user);
                entityManager.merge(user);
            } else {
                log.debug("Inserting entity to repository. New User: {}", user);
                entityManager.persist(user);
            }
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            log.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public void delete(User user) {
        if (entityManager.find(User.class, user.getId()) != null) {
            try {
                log.info("Deleting User entity from repository: {}", user);
                entityManager.getTransaction().begin();
                entityManager.remove(user);
                entityManager.flush();
                entityManager.getTransaction().commit();
            } catch (PersistenceException e) {
                log.error(e.getMessage(), e);
            }
        } else {
            log.info("Entity is not presented in repository: {}", user);
        }
    }
}
