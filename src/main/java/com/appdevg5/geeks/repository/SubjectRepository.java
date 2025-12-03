package com.appdevg5.geeks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.appdevg5.geeks.entity.SubjectEntity;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer>{
    
    @Query("SELECT s FROM SubjectEntity s WHERE s.subject_name = :subjectName")
    Optional<SubjectEntity> findBySubjectName(@Param("subjectName") String subjectName);
    
    @Query("SELECT s FROM SubjectEntity s WHERE s.subject_code = :subjectCode")
    Optional<SubjectEntity> findBySubjectCode(@Param("subjectCode") String subjectCode);
}