package com.example.demo.Users;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.AppServices.JwtService;
import com.example.demo.AppServices.PasswordService;
import com.example.demo.Helpers.PasswordGenerator;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordGenerator passwordGenerator;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final PasswordService passwordService;
    private final JwtService jwtService;
    @Autowired
    public UserService (UserRepository userRepository , PasswordGenerator passwordGenerator  , PasswordService passwordService , JwtService jwtService){
        this.userRepository= userRepository;
        this.passwordGenerator= passwordGenerator;
        this.passwordService = passwordService;
        this.jwtService = jwtService;
    }

    @Transactional
    public UserDTO createUser (UserCreationDTO userDTO){
        try {
            userDTO.setPassword(this.passwordGenerator.generatePassword());
            User userInserted = this.userRepository.save(User.fromCreationDTO(userDTO));
            return UserDTO.fromEntity(userInserted);
        } catch (Exception e) {
           logger.error(e.getMessage(), e);
           throw new RuntimeException(e);
        }
    }

    @Transactional
    public UserDTO signUpUserService (UserCreationDTO userDTO){
        try {
            String passwordToEncrypt  = userDTO.getPassword();
            userDTO.setPassword(this.passwordService.encryptPassword(passwordToEncrypt));
            User userInserted = this.userRepository.save(User.fromCreationDTO(userDTO));
            return UserDTO.fromEntity(userInserted);
        } catch (Exception e) {
           logger.error(e.getMessage(), e);
           throw new RuntimeException(e);
        }
    }

        @Transactional
        public Map<String ,?> logInUserService (Map<String ,String> signInData){
            try {
                String email= signInData.get("email");
                String password= signInData.get("password");
                User userIfexists = this.userRepository.findByEmailAddress(email).orElseThrow(()-> new RuntimeException("User With this email does not exisit"));
                boolean passwordMatch = this.passwordService.verifyPassword(password,userIfexists.getPassword());
                if (!passwordMatch){
                    return Map.of("result","password is not correct");
                }
                String jwtToken = this.jwtService.generateToken(email, userIfexists.getRole(), userIfexists.getId());
                return Map.of("user",UserDTO.fromEntity(userIfexists), "token", jwtToken);
            } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
            }
        }


    @Transactional
    public List<UserDTO> findAllUsers (){
        try {
            List<User> users= this.userRepository.findAll();
            return users.stream()
                        .map(UserDTO::fromEntity)
                        .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public Optional<UserDTO> findOneUser (long id){
        try {
            Optional<User> user= this.userRepository.findById(id);
            return user.map(UserDTO::fromEntity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    
    @Transactional
    public String destroyOneUser (long id){
        try {
            this.userRepository.deleteById(id);;
            return "User has been deleted";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public String modifyOneUserService (long id , UserCreationDTO data){
        try {
        if (data !=null){
            User userToModify = this.userRepository.findById(id).orElseThrow(()-> new RuntimeException("The User was not found"));
            UserCreationDTO dto = UserCreationDTO.fromEntity(userToModify);
            
               if (data.getFirstName()!=null && !data.getFirstName().equals(dto.getFirstName())){
                 dto.setFirstName(data.getFirstName());
               }
               if (data.getLastName()!=null && ! data.getLastName().equals(dto.getLastName())){
                dto.setLastName(data.getLastName());
               }
               if (data.getEmailAddress()!=null && ! data.getEmailAddress().equals(dto.getEmailAddress())){
                dto.setEmailAddress(data.getEmailAddress());
               }
               if (data.getPhoneNumber()!=null && ! data.getPhoneNumber().equals(dto.getPhoneNumber())){
                dto.setPhoneNumber(data.getPhoneNumber());
               }
               if (data.getOccupation()!=null && ! data.getOccupation().equals(dto.getOccupation())){
                dto.setOccupation(data.getOccupation());
               }
               if (data.getRole()!=null && ! data.getRole().equals(dto.getRole())){
                dto.setRole(data.getRole());
               }
               this.userRepository.save(User.fromCreationDTO(dto));
              return "User has been Modified"; 
            }
            return "The Object that you have inserted is null";
            
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    
}
