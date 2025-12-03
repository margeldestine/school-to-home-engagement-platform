package com.appdevg5.geeks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.appdevg5.geeks.entity.TeacherEntity;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer> {
    @Query("SELECT t FROM TeacherEntity t WHERE t.user.user_id = :userId")
    Optional<TeacherEntity> findByUserId(@Param("userId") int userId);
}