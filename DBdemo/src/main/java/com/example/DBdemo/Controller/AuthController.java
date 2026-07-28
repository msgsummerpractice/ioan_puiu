package com.example.DBdemo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DBdemo.Service.AuthService;
import com.example.DBdemo.dto.SignInRequest;
import com.example.DBdemo.dto.SignInResponse;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<SignInResponse> login(@RequestBody SignInRequest loginDto){

        //01 - Receive the token from AuthService
        SignInResponse token = authService.login(loginDto);

        //02 - Set the token as a response using JwtAuthResponse Dto class
        // SignInResponse authResponseDto = new SignInResponse();
        // authResponseDto.setToken(token);

        //03 - Return the response to the user
        return ResponseEntity.ok(token);
    }
}