package com.example.DBdemo.Service;

import com.example.DBdemo.Repository.UserRepository;
import com.example.DBdemo.config.security.JwtTokenProvider;
import java.util.Optional;
import com.example.DBdemo.config.security.CustomUserDetails;
import com.example.DBdemo.dto.SignInResponse;

import java.util.Random;
import com.example.DBdemo.config.security.UserDetailsImplementation;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.DBdemo.Model.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MfaServiceImpl  implements MfaService {

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserDetailsImplementation userDetailsService;


    private final Random random = new Random();


    @Override
    public String generateMfaSecret(String username) {
        // Implement the logic to generate MFA secret for the user
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        User actualUser = user.get();

        String generatedSecret = String.valueOf(random.nextInt(1000000)); // Generate a random 6-digit code
        actualUser.setMfaCode(generatedSecret);
        userRepository.save(actualUser);
        System.out.println("Generated MFA code for user " + username + ": " + generatedSecret);
        return generatedSecret;

    }

    @Override
    public SignInResponse verifyMfaCode(String username, String code) {

        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 03 - Generate the token based on username and secret key
        String token = jwtTokenProvider.generateToken(authentication);
        
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 04 - Return the token to controller
        SignInResponse signInResponse = new SignInResponse();
        // Implement the logic to verify the MFA code for the user
        // For demonstration purposes, let's assume any code "123456" is valid
        if (user.getMfaCode().equals(code)) {
            signInResponse.setToken(token);
            signInResponse.setRole(user.getRoles().stream().map(role -> role.getName()).collect(java.util.stream.Collectors.toSet()));
            user.setMfaCode(null);
            userRepository.save(user);
            return signInResponse;
        } else {
            throw new IllegalArgumentException("Invalid MFA code");
        }
    }



}
