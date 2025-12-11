package com.appdevg5.geeks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appdevg5.geeks.entity.AttendanceEntity;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Integer>{
    @Query("SELECT a FROM AttendanceEntity a WHERE a.student.student_id = :studentId")
    List<AttendanceEntity> findByStudentId(@Param("studentId") int studentId);

    @Query("SELECT a FROM AttendanceEntity a WHERE a.student.student_id = :studentId AND DATE(a.attendance_date) = DATE(:date)")
    List<AttendanceEntity> findByStudentIdAndDate(@Param("studentId") int studentId, @Param("date") Timestamp date);
}
