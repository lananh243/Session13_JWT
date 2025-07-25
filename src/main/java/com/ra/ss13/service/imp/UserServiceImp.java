package com.ra.ss13.service.imp;

import com.ra.ss13.model.dto.request.LoginRequest;
import com.ra.ss13.model.dto.request.RegisterRequest;
import com.ra.ss13.model.dto.request.UserUpdateDTO;
import com.ra.ss13.model.entity.User;
import com.ra.ss13.model.utils.Role;
import com.ra.ss13.repository.UserRepository;
import com.ra.ss13.security.jwt.JWTProvider;
import com.ra.ss13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTProvider jwtProvider;
    @Override
    public User register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username đã tồn tại!");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setStatus(true);
        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Sai mat khau");
        }
        return jwtProvider.generateToken(user.getUsername(),  user.getRole());
    }

    @Override
    public void updateProfile(String username, UserUpdateDTO updateDTO) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));

        if (updateDTO.getEmail() != null) {
            user.setEmail(updateDTO.getEmail());
        }

        if (updateDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updateDTO.getPassword()));
        }

        userRepository.save(user);
    }
}
