package com.example.demo.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    
    @Id
    @Column(name = "id" ,unique = true , nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(name = "firstName" ,nullable = false)
    private String firstName;
    @Column(name = "lastName" ,nullable = false)
    private String lastName;
    @Column(name = "phoneNumber" ,nullable = false)
    private String phoneNumber; 
    @Column(name = "emailAddress" ,nullable = false)
    private String emailAddress;
    @Column(name = "occupation" ,nullable = false)
    private String occupation;
    @Column(name="password" , nullable = false )
    private String password;
    public static User fromDTO(UserDTO userDTO){
        return new User(
           userDTO.getId(),
           userDTO.getFirstName(),
           userDTO.getLastName(),
           userDTO.getPhoneNumber(),
           userDTO.getEmailAddress(),
           userDTO.getOccupation(),
           userDTO.getPassword()
        );
    }

}