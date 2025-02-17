package com.example.myEcomProjectPractice.ServicesImpl;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.myEcomProjectPractice.DTO.ChangePasswordRequest;
import com.example.myEcomProjectPractice.Models.User;
import com.example.myEcomProjectPractice.Repo.UserRepo;
import com.example.myEcomProjectPractice.Services.UserService;
import com.example.myEcomProjectPractice.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registUser(User user) {
        if(userRepo.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalStateException("User already exists");
        }

        user.setUserpassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.USER);
        return userRepo.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        //return userRepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        return userRepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
    }

    @Override
    public void changePassword(String email, ChangePasswordRequest request) {
        User user = userRepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        if(!passwordEncoder.matches(request.getCurrentPassword(),user.getPassword()))
        {
            throw new BadCredentialsException("Current Password is incorrect");
        }

        user.setUserpassword(passwordEncoder.encode(request.getNewPassword()));
        userRepo.save(user);

    }
    
}
