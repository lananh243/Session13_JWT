package com.ra.ss13.controller;

import com.ra.ss13.model.dto.response.APIResponse;
import com.ra.ss13.security.jwt.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private JWTProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<APIResponse<?>> login(@RequestParam String username, @RequestParam String password){
        if ("admin".equals(username) && "12345".equals(password)){
            String token = jwtProvider.generateToken(username);
            return new ResponseEntity<>(new APIResponse<>(true, "Bearer", token, HttpStatus.OK), HttpStatus.OK);
        }
        return new ResponseEntity<>(new APIResponse<>(false, "Đăng nhập thất bại", null, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<APIResponse<?>> hello(){
        return new ResponseEntity<>(new APIResponse<>(true, "Hello! Bạn đã xác thực thành công.",null, HttpStatus.OK), HttpStatus.OK);
    }
}
