package com.example.DBdemo.Service;
import com.example.DBdemo.dto.SignInRequest;
import com.example.DBdemo.dto.SignInResponse;

public interface AuthService {
    SignInResponse login(SignInRequest signInRequest);
}