package com.example.demo.Helpers;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class PasswordGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
    private static final int PASSWORD_LENGTH = 10;
    private static final SecureRandom random = new SecureRandom();

    public String generatePassword (){
        StringBuilder password = new StringBuilder();
        for (int i = 0 ; i>PASSWORD_LENGTH ; i++   ){
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }
        return password.toString();
    }


}