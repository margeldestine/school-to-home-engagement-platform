package com.appdevg5.geeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.appdevg5.geeks.repository.UserRepository;
import com.appdevg5.geeks.repository.TeacherRepository;
import com.appdevg5.geeks.repository.ParentRepository;
import com.appdevg5.geeks.repository.StudentRepository;
import com.appdevg5.geeks.entity.UserEntity;
import com.appdevg5.geeks.entity.TeacherEntity;
import com.appdevg5.geeks.entity.ParentEntity;
import com.appdevg5.geeks.entity.StudentEntity;
import com.appdevg5.geeks.dto.RegisterRequestDTO;
import com.appdevg5.geeks.dto.LoginRequestDTO;
import com.appdevg5.geeks.dto.AuthResponseDTO;
import com.appdevg5.geeks.exception.StudentNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponseDTO register(RegisterRequestDTO dto) {
        if ("PARENT".equalsIgnoreCase(dto.getRole())) {
            return registerParent(dto);
        }
        return registerUser(dto);
    }

    public AuthResponseDTO login(LoginRequestDTO dto) {
        UserEntity user = userRepository.findByEmail(dto.getEmail())
            .orElseThrow(() -> new NoSuchElementException("Invalid email or password"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new NoSuchElementException("Invalid email or password");
        }
        return new AuthResponseDTO(user.getUser_id(), user.getFirst_name(), user.getLast_name(), user.getRole());
    }

    @Transactional
    private AuthResponseDTO registerParent(RegisterRequestDTO dto) {
        if (dto.getStudentNumber() == null || dto.getStudentNumber().trim().isEmpty()) {
            throw new StudentNotFoundException("Student number is required");
        }

        Optional<StudentEntity> studentOpt = studentRepository.findByStudentNumber(dto.getStudentNumber());
        if (studentOpt.isEmpty()) {
            throw new StudentNotFoundException("Student number not found. Please check and try again.");
        }
        StudentEntity student = studentOpt.get();

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        UserEntity user = new UserEntity();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFirst_name(dto.getFirstName());
        user.setLast_name(dto.getLastName());
        user.setRole("PARENT");
        user.setCreated_at(new Timestamp(System.currentTimeMillis()));
        UserEntity savedUser = userRepository.save(user);

        ParentEntity parent = new ParentEntity();
        parent.setUser(savedUser);
        parent.setStudent(student);
        ParentEntity savedParent = parentRepository.save(parent);

        AuthResponseDTO response = new AuthResponseDTO();
        response.setUserId(savedUser.getUser_id());
        response.setEmail(savedUser.getEmail());
        response.setFirstName(savedUser.getFirst_name());
        response.setLastName(savedUser.getLast_name());
        response.setRole("PARENT");
        response.setParentId(savedParent.getParent_id());
        response.setStudentId(student.getStudent_id());
        response.setToken("DUMMY_TOKEN_" + savedUser.getUser_id());
        response.setMessage("Registration successful");
        return response;
    }

    @Transactional
    private AuthResponseDTO registerUser(RegisterRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        String role = dto.getRole();
        role = role == null ? "TEACHER" : role.toUpperCase();

        UserEntity user = new UserEntity();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFirst_name(dto.getFirstName());
        user.setLast_name(dto.getLastName());
        user.setRole(role);
        user.setCreated_at(new Timestamp(System.currentTimeMillis()));
        UserEntity saved = userRepository.save(user);

        if (role.equals("TEACHER")) {
            TeacherEntity teacher = new TeacherEntity();
            teacher.setUser(saved);
            teacherRepository.save(teacher);
        } else if (role.equals("PARENT")) {
            ParentEntity parent = new ParentEntity();
            parent.setUser(saved);
            parentRepository.save(parent);
        }

        AuthResponseDTO response = new AuthResponseDTO();
        response.setUserId(saved.getUser_id());
        response.setEmail(saved.getEmail());
        response.setFirstName(saved.getFirst_name());
        response.setLastName(saved.getLast_name());
        response.setRole(saved.getRole());
        response.setToken("DUMMY_TOKEN_" + saved.getUser_id());
        response.setMessage("Registration successful");
        return response;
    }
}
