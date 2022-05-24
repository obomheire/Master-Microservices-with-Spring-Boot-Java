package com.jpa.basic.jpain10steps.service;

import com.jpa.basic.jpain10steps.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long>{
    
}
