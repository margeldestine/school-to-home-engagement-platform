package com.appdevg5.geeks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.appdevg5.geeks.entity.StudentEntity;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    @Query("SELECT s FROM StudentEntity s WHERE s.student_number = :student_number")
    Optional<StudentEntity> findByStudentNumber(@Param("student_number") String student_number);

    @Query("SELECT s FROM StudentEntity s WHERE s.section.section_id = :sectionId")
    List<StudentEntity> findBySectionId(@Param("sectionId") int sectionId);
}
