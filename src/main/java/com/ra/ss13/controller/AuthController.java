package com.ra.ss13.controller;

import com.ra.ss13.model.dto.request.LoginRequest;
import com.ra.ss13.model.dto.request.RegisterRequest;
import com.ra.ss13.model.dto.response.APIResponse;
import com.ra.ss13.security.jwt.JWTProvider;
import com.ra.ss13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse<?>> login(@RequestBody LoginRequest request){
        return new ResponseEntity<>(new APIResponse<>(false, "Đăng nhập thanh cong", userService.login(request), HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse<?>> register(@RequestBody RegisterRequest request){
        return new ResponseEntity<>(new APIResponse<>(true, "Dang ki thanh cong", userService.register(request), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<APIResponse<?>> hello(){
        return new ResponseEntity<>(new APIResponse<>(true, "Hello! Bạn đã xác thực thành công.",null, HttpStatus.OK), HttpStatus.OK);
    }
}
