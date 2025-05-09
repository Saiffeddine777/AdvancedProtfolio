package com.example.demo.Users;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> postUserController(@RequestBody UserDTO userDTO){
        try {
            UserDTO userDTOInserted = this.userService.createUser(userDTO);
            return  ResponseEntity.status(HttpStatus.CREATED).body(userDTOInserted);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to create The User" + e.getMessage());           
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signUpUserController(@RequestBody UserDTO userDTO){
        try {
            UserDTO userDTOInserted = this.userService.signUpUserService(userDTO);
            return  ResponseEntity.status(HttpStatus.CREATED).body(userDTOInserted);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to create The User" + e.getMessage());           
        }
    }

    

    @GetMapping("/all")
    public ResponseEntity<?> getUsersController(){
        try {
            List<UserDTO> userDTOs = this.userService.findAllUsers();
            return  ResponseEntity.status(HttpStatus.OK).body(userDTOs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to fetch The Users" + e.getMessage());           
        }
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<?> getOneUserController(@PathVariable("id") long id){
        try {
            Optional<UserDTO> userDTO = this.userService.findOneUser(id);
            if(userDTO.isPresent()){
                return  ResponseEntity.status(HttpStatus.OK).body(userDTO.get());
            }
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("User is not found");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to fetch The Users" + e.getMessage());           
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOneUser (@PathVariable("id") long id){
        try {
            String result = this.userService.destroyOneUser(id);
            return  ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to fetch The Users" + e.getMessage());           
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> putOneUser (@PathVariable("id") long id , @RequestBody UserDTO data){
        try {
            String result = this.userService.modifyOneUserService(id , data);
            return  ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            if(e.getMessage()=="The User was not found"){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());      
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to modify The User" + e.getMessage());           
        }
    }


    


    
    
}