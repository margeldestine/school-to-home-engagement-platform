package com.appdevg5.geeks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appdevg5.geeks.entity.GradeEntity;
import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, Integer>{
    
    @Query("SELECT g FROM GradeEntity g WHERE g.student.student_id = :studentId")
    List<GradeEntity> findByStudentId(@Param("studentId") int studentId);
    
    @Query("SELECT g FROM GradeEntity g WHERE g.student.student_id = :studentId AND g.grading_period = :gradingPeriod")
    List<GradeEntity> findByStudentIdAndGradingPeriod(@Param("studentId") int studentId, @Param("gradingPeriod") int gradingPeriod);
    
    @Query("SELECT g FROM GradeEntity g WHERE g.student.section.section_id = :sectionId")
    List<GradeEntity> findBySection(@Param("sectionId") int sectionId);
    
    @Query("SELECT g FROM GradeEntity g WHERE g.student.student_id = :studentId " + 
           "AND g.subject.subject_id = :subjectId AND g.grading_period = :quarter")
    List<GradeEntity> findByStudentSubjectQuarter(
        @Param("studentId") int studentId, 
        @Param("subjectId") int subjectId, 
        @Param("quarter") int quarter
    );
    
    @Query("SELECT g FROM GradeEntity g WHERE g.student.student_id = :studentId AND g.subject.subject_id = :subjectId AND g.grading_period = :gradingPeriod")
    Optional<GradeEntity> findByStudentIdAndSubjectIdAndGradingPeriod(
        @Param("studentId") int studentId, 
        @Param("subjectId") int subjectId, 
        @Param("gradingPeriod") int gradingPeriod
    );
}