package com.epam.starbun.todolist.repository;

import com.epam.starbun.todolist.domain.Attachment;
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
public class AttachmentRepository implements CommonRepository<Attachment> {

    private final EntityManager entityManager;

    @Override
    public Optional<Attachment> findById(UUID id) {
        log.debug("Finding Attachment entity by id={}", id);
        try {
            return Optional.ofNullable(entityManager.find(Attachment.class, id));
        } catch (PersistenceException e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Attachment> findAll() {
        try {
            log.debug("Finding all Attachment entities in repository");
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Attachment> query = cb.createQuery(Attachment.class);
            Root<Attachment> c = query.from(Attachment.class);
            query.select(c);
            List<Attachment> resultList = entityManager.createQuery(query).getResultList();
            return resultList;
        } catch (PersistenceException e) {
            log.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public Attachment save(Attachment attachment) {
        try {
            entityManager.getTransaction().begin();
            if (entityManager.find(Attachment.class, attachment.getId()) != null) {
                log.debug("Updating entity in repository. Updated Attachment: {}", attachment);
                entityManager.merge(attachment);
            } else {
                log.debug("Inserting entity to repository. New Attachment: {}", attachment);
                entityManager.persist(attachment);
            }
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            log.error(e.getMessage(), e);
        }
        return attachment;
    }

    @Override
    public void delete(Attachment attachment) {
        if (entityManager.find(Attachment.class, attachment.getId()) != null) {
            try {
                log.info("Deleting Attachment entity from repository: {}", attachment);
                entityManager.getTransaction().begin();
                entityManager.remove(attachment);
                entityManager.flush();
                entityManager.getTransaction().commit();
            } catch (PersistenceException e) {
                log.error(e.getMessage(), e);
            }
        } else {
            log.info("Entity is not presented in repository: {}", attachment);
        }
    }
}
