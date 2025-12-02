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
        };
    }
}
