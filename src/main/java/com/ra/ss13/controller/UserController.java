package com.ra.ss13.controller;

import com.ra.ss13.model.dto.request.UserUpdateDTO;
import com.ra.ss13.model.dto.response.APIResponse;
import com.ra.ss13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("/profile/edit")
    public ResponseEntity<APIResponse<?>> update(@RequestBody UserUpdateDTO userUpdateDTO, Authentication auth){
        userService.updateProfile(auth.getName(), userUpdateDTO);
        return new ResponseEntity<>(
                new APIResponse<>(true, "Cập nhật thành công", null, HttpStatus.OK),
                HttpStatus.OK
        );
    }
}
