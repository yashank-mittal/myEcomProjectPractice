package com.example.myEcomProjectPractice.Services;

import com.example.myEcomProjectPractice.DTO.ChangePasswordRequest;
import com.example.myEcomProjectPractice.Models.User;

public interface UserService {
    User registUser(User user);
    User findUserByEmail(String email);
    void changePassword(String email,ChangePasswordRequest request);
}
