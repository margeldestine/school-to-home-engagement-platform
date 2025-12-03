package com.appdevg5.geeks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.appdevg5.geeks.entity.ParentEntity;
import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<ParentEntity, Integer> {
    @Query("SELECT p FROM ParentEntity p WHERE p.user.user_id = :userId")
    Optional<ParentEntity> findByUserId(@Param("userId") int userId);
}