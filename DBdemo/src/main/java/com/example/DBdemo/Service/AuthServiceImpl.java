package com.example.DBdemo.Service;
import com.example.DBdemo.config.security.JwtTokenProvider;
import com.example.DBdemo.dto.SignInRequest;
import com.example.DBdemo.dto.SignInResponse;

import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    
    private AuthenticationManager authenticationManager;
    
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public SignInResponse login(SignInRequest signInRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),
                signInRequest.getPassword()
        ));

        // 01 - AuthenticationManager is used to authenticate the use

        /* 02 - SecurityContextHolder is used to allows the rest of the application to know
        that the user is authenticated and can use user data from Authentication object */
        
        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setMfaEnabled(true); // Set MFA enabled to true

        return signInResponse;
    }

}
