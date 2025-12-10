package com.appdevg5.geeks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.appdevg5.geeks.entity.BehaviorLogEntity;

@Repository
public interface BehaviorLogRepository extends JpaRepository<BehaviorLogEntity, Integer> {
    @Query("SELECT b FROM BehaviorLogEntity b WHERE b.student.student_id = :studentId ORDER BY b.incident_date DESC")
    java.util.List<BehaviorLogEntity> findByStudentIdOrderByIncidentDateDesc(@Param("studentId") int studentId);
}

