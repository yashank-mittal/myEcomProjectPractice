package com.example.myEcomProjectPractice.Services;

import com.example.myEcomProjectPractice.DTO.ChangePasswordRequest;
import com.example.myEcomProjectPractice.Models.User;

public interface UserService {
    User registerUser(User user);
    User getUserByEmail(String email);
    void changePassword(String email, ChangePasswordRequest request);
    void confirmEmail(String email, String confirmationCode);
    User getUserById(Long id);
}
