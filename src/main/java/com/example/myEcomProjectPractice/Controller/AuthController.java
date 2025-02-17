package com.example.myEcomProjectPractice.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myEcomProjectPractice.DTO.ChangePasswordRequest;
import com.example.myEcomProjectPractice.DTO.LoginRequest;
import com.example.myEcomProjectPractice.Models.User;
import com.example.myEcomProjectPractice.Services.JwtService;
import com.example.myEcomProjectPractice.Services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authManager;
    private final UserService userService;
    private final JwtService jwtService;

    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest)
    {
        Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getUserpassword()));
        final UserDetails userDetails = userService.findUserByEmail(loginRequest.getEmail());
        if(authentication.isAuthenticated())
        {
            final String jwtToken = jwtService.generateToken(userDetails);
            return ResponseEntity.ok(jwtToken);
        }
        return ResponseEntity.ok("Invalid credentials");
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user)
    {
        return ResponseEntity.ok(userService.registUser(user));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        userService.changePassword(email, request);
        return ResponseEntity.ok("Password changed successfully");
    }
    
}
