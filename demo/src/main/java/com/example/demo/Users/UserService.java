package com.example.demo.Users;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    public UserService (UserRepository userRepository){
        this.userRepository= userRepository;
    }

    @Transactional
    public UserDTO createUser (UserDTO userDTO){
        try {
            User userInserted = this.userRepository.save(User.fromDTO(userDTO));
            return UserDTO.fromEntity(userInserted);
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
    public String modifyOneUserService (long id , UserDTO data){
        try {
        if (data !=null){
            User userToModify = this.userRepository.findById(id).orElseThrow(()-> new RuntimeException("The User was not found"));
            UserDTO dto = UserDTO.fromEntity(userToModify);
            
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
               this.userRepository.save(User.fromDTO(dto));
              return "User has been Modified"; 
            }
            return "The Object that you have inserted is null";
            
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    
}
