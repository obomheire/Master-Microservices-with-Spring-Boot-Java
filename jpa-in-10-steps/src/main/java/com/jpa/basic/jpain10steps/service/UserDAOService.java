package com.jpa.basic.jpain10steps.service;

import com.jpa.basic.jpain10steps.entity.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDAOService {

    @PersistenceContext
    private EntityManager entityManager;

    public long insert(Users user) {
        entityManager.persist(user);
        return user.getId();
    }
}
