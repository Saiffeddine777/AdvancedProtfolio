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

public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber; 
    private String emailAddress;
    private String occupation;
    private Role role;
    
    
    public static UserDTO fromEntity(User user){
        return new UserDTO(
           user.getId(),
           user.getFirstName(),
           user.getLastName(),
           user.getPhoneNumber(),
           user.getEmailAddress(),
           user.getOccupation(),
           user.getRole()
        );
    }
}