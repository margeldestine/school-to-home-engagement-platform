package com.appdevg5.geeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appdevg5.geeks.repository.UserRepository;
import com.appdevg5.geeks.entity.UserEntity;
import com.appdevg5.geeks.dto.RegisterRequestDTO;
import com.appdevg5.geeks.dto.LoginRequestDTO;
import com.appdevg5.geeks.dto.AuthResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.sql.Timestamp;
import java.util.NoSuchElementException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponseDTO register(RegisterRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        UserEntity user = new UserEntity();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFirst_name(dto.getFirstName());
        user.setLast_name(dto.getLastName());
        user.setRole(dto.getRole());
        user.setCreated_at(new Timestamp(System.currentTimeMillis()));

        UserEntity saved = userRepository.save(user);
        return new AuthResponseDTO(saved.getUser_id(), saved.getFirst_name(), saved.getLast_name(), saved.getRole());
    }

    public AuthResponseDTO login(LoginRequestDTO dto) {
        UserEntity user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new NoSuchElementException("Invalid email or password"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new NoSuchElementException("Invalid email or password");
        }
        return new AuthResponseDTO(user.getUser_id(), user.getFirst_name(), user.getLast_name(), user.getRole());
    }
}

