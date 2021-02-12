package com.epam.starbun.todolist.domain.repository;

import com.epam.starbun.todolist.domain.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
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
public class NoteRepository implements CommonRepository<Note> {
    private final EntityManager entityManager = Persistence
            .createEntityManagerFactory("supplier-pu")
            .createEntityManager();

    private final CriteriaBuilder cb = entityManager.getCriteriaBuilder();

    @Override
    public Optional<Note> findById(UUID id) {
        log.debug("Finding Note entity by id={}", id);
        try {
            return Optional.ofNullable(entityManager.find(Note.class, id));
        } catch (PersistenceException e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Note> findAll() {
        try {
            log.debug("Finding all Note entities in repository");
            CriteriaQuery<Note> query = cb.createQuery(Note.class);
            Root<Note> c = query.from(Note.class);
            query.select(c);
            List<Note> resultList = entityManager.createQuery(query).getResultList();
            return resultList;
        } catch (PersistenceException e) {
            log.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public Note save(Note note) {
        try {
            entityManager.getTransaction().begin();
            if (entityManager.find(Note.class, note.getId()) != null) {
                log.debug("Updating entity in repository. Updated Note: {}", note);
                entityManager.merge(note);
            } else {
                log.debug("Inserting entity to repository. New Note: {}", note);
                entityManager.persist(note);
            }
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            log.error(e.getMessage(), e);
        }
        return note;
    }

    @Override
    public void delete(Note note) {
        if (entityManager.find(Note.class, note.getId()) != null) {
            try {
                log.info("Deleting Note entity from repository: {}", note);
                entityManager.getTransaction().begin();
                entityManager.remove(note);
                entityManager.flush();
                entityManager.getTransaction().commit();
            } catch (PersistenceException e) {
                log.error(e.getMessage(), e);
            }
        } else {
            log.info("Entity is not presented in repository: {}", note);
        }
    }
}
