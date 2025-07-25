package com.ra.ss13.service;

import com.ra.ss13.model.dto.request.LoginRequest;
import com.ra.ss13.model.dto.request.RegisterRequest;
import com.ra.ss13.model.dto.request.UserUpdateDTO;
import com.ra.ss13.model.entity.User;

public interface UserService {
    User register(RegisterRequest request);
    String login(LoginRequest request);
    void updateProfile(String username, UserUpdateDTO updateDTO);
}
