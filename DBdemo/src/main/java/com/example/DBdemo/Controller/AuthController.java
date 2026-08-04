package com.example.DBdemo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DBdemo.Service.AuthService;
import com.example.DBdemo.Service.MfaService;
import com.example.DBdemo.dto.MfaSignInRequest;
import com.example.DBdemo.dto.SignInRequest;
import com.example.DBdemo.dto.SignInResponse;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    private MfaService mfaService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<SignInResponse> login(@RequestBody SignInRequest loginDto){

        //01 - Receive the token from AuthService
        SignInResponse token = authService.login(loginDto);

        //02 - Set the token as a response using JwtAuthResponse Dto class
        // SignInResponse authResponseDto = new SignInResponse();
        // authResponseDto.setToken(token);

        //03 - Return the response to the user
        mfaService.generateMfaSecret(loginDto.getUsername()); // Generate MFA secret for the user

        token.setMfaEnabled(true); // Indicate that MFA is enabled

        return ResponseEntity.ok(token);
    }

    @PostMapping("/mfalogin")
    public ResponseEntity<SignInResponse> mfaLogin(@RequestBody MfaSignInRequest loginDto) {
        // Implement MFA login logic here
        // For now, just return a placeholder response
        SignInResponse response = mfaService.verifyMfaCode(loginDto.getUsername(), loginDto.getMfaCode());
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            // If MFA code is invalid, return an error response
            return ResponseEntity.status(401).build(); // Unauthorized
        }


    }
}