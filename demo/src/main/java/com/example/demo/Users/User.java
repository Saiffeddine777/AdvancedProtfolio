package com.example.demo.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    public enum Role {
        Admin, visitor
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name = "emailAddress", nullable = false, unique = true)
    private String emailAddress;
    @Column(name = "occupation", nullable = false)
    private String occupation;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public static User fromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmailAddress(userDTO.getEmailAddress());
        user.setOccupation(userDTO.getOccupation());
        user.setRole(userDTO.getRole());
        return user;
    }

    public static User fromCreationDTO(UserCreationDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getPhoneNumber(),
                userDTO.getEmailAddress(),
                userDTO.getOccupation(),
                userDTO.getPassword(),
                userDTO.getRole());
    }

}