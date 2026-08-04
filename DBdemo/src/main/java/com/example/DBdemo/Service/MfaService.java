package com.example.DBdemo.Service;

import com.example.DBdemo.dto.SignInResponse;

public interface MfaService {
    public String generateMfaSecret(String username);

    public SignInResponse verifyMfaCode(String username, String code);

}
