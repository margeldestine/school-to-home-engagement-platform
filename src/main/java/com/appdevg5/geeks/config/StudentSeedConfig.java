package com.appdevg5.geeks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import com.appdevg5.geeks.repository.StudentRepository;
import com.appdevg5.geeks.entity.StudentEntity;
import java.util.Arrays;

@Configuration
public class StudentSeedConfig {

    @Bean
    public CommandLineRunner seedStudents(StudentRepository studentRepository, JdbcTemplate jdbcTemplate) {
        return args -> {
            try {
                jdbcTemplate.execute("ALTER TABLE student_entity MODIFY COLUMN student_number VARCHAR(20)");
            } catch (Exception ignored) {
            }
            if (studentRepository.count() == 0) {
                StudentEntity s1 = new StudentEntity();
                s1.setStudent_number("2025-00001");
                s1.setFirst_name("Francaryllese");
                s1.setLast_name("Dacabelam");
                s1.setGrade_level(4);

                StudentEntity s2 = new StudentEntity();
                s2.setStudent_number("2025-00002");
                s2.setFirst_name("Ritchie Marie");
                s2.setLast_name("Something");
                s2.setGrade_level(4);

                StudentEntity s3 = new StudentEntity();
                s3.setStudent_number("03-2025-001");
                s3.setFirst_name("Sample");
                s3.setLast_name("Student");
                s3.setGrade_level(3);

                StudentEntity s4 = new StudentEntity();
                s4.setStudent_number("2025-00003");
                s4.setFirst_name("Ana");
                s4.setLast_name("Reyes");
                s4.setGrade_level(5);

                StudentEntity s5 = new StudentEntity();
                s5.setStudent_number("2025-00004");
                s5.setFirst_name("Miguel");
                s5.setLast_name("Santos");
                s5.setGrade_level(6);

                studentRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5));
            }
        };
    }
}
