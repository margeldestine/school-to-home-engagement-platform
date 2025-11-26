package com.appdevg5.geeks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.appdevg5.geeks.entity.ParentEntity;

@Repository
public interface ParentRepository extends JpaRepository<ParentEntity, Integer> {
}
