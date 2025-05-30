package com.example.demo.Users;

import com.example.demo.Users.User.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserCreationDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber; 
    private String emailAddress;
    private String occupation;
    private String password;
    private Role role;
    
    
    public static UserCreationDTO fromEntity(User user){
        return new UserCreationDTO(
           user.getId(),
           user.getFirstName(),
           user.getLastName(),
           user.getPhoneNumber(),
           user.getEmailAddress(),
           user.getOccupation(),
           user.getPassword(),
           user.getRole()
        );
    }
}