package com.example.DBdemo.Service;
import com.example.DBdemo.config.security.JwtTokenProvider;
import com.example.DBdemo.dto.SignInRequest;
import com.example.DBdemo.dto.SignInResponse;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public SignInResponse login(SignInRequest signInRequest) {

        // 01 - AuthenticationManager is used to authenticate the user
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),
                signInRequest.getPassword()
        ));

        /* 02 - SecurityContextHolder is used to allows the rest of the application to know
        that the user is authenticated and can use user data from Authentication object */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 03 - Generate the token based on username and secret key
        String token = jwtTokenProvider.generateToken(authentication);

        // 04 - Return the token to controller
        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setToken(token);
        signInResponse.setRole(authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toSet()));
        return signInResponse;
    }

}
