package com.example.demo.AppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordService (PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder ;
    }

    public String encryptPassword(String password){
        return passwordEncoder.encode(password);
    }

    public boolean verifyPassword (String frontPassword , String encodedPassword){
        return passwordEncoder.matches(frontPassword , encodedPassword);
    }
    
}