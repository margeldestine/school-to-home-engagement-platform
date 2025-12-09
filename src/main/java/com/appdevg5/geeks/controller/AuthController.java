package com.appdevg5.geeks.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import com.appdevg5.geeks.service.AuthService;
import com.appdevg5.geeks.dto.RegisterRequestDTO;
import com.appdevg5.geeks.dto.LoginRequestDTO;
import com.appdevg5.geeks.dto.AuthResponseDTO;
import com.appdevg5.geeks.repository.StudentRepository;
import com.appdevg5.geeks.entity.StudentEntity;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/register")
    public AuthResponseDTO register(@RequestBody RegisterRequestDTO dto) {
        return authService.register(dto);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginRequestDTO dto) {
        return authService.login(dto);
    }

    @PostMapping("/validate-student-number")
    public Map<String, Object> validateStudentNumber(@RequestBody Map<String, Object> payload) {
        String studentNumber = (String) payload.get("student_number");
        Optional<StudentEntity> opt = studentRepository.findByStudentNumber(studentNumber);
        if (opt.isPresent()) {
            StudentEntity s = opt.get();
            return Map.of(
                "exists", true,
                "student_id", s.getStudent_id(),
                "student_name", s.getFirst_name() + " " + s.getLast_name()
            );
        }
        return Map.of("exists", false);
    }
}
