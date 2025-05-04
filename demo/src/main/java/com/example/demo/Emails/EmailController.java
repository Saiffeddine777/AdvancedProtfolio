package com.example.demo.Emails;

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
@RequestMapping("/api/emails")
public class EmailController {
    private final EmailService emailService;
    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> postEmailController (@RequestBody EmailDTO emailDTO){
        try {
            EmailDTO emailInserted = this.emailService.createOneEmail(emailDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(emailInserted); 
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create the Emmail: " + e.getMessage()); 
        }
    }
        @GetMapping("/all")
    public ResponseEntity<?> getEmailsController(){
        try {
           List<EmailDTO> emailDTOs = this.emailService.findAllEmails();
           return ResponseEntity.status(HttpStatus.OK).body(emailDTOs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to fetch emails" + e.getMessage());
        }
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<?> getOneEmailController(@PathVariable("id") String id){
        try {
           Optional<EmailDTO> optionalEmailDTO = this.emailService.findOneEmail(id);
           if (optionalEmailDTO.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalEmailDTO.get());
           }
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email Is not found");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to fetch The email" + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOneEmailController(@PathVariable("id") String id){
        try {
            String result = this.emailService.destroyOneEmail(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to delete The email" + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOneEmailController(@PathVariable("id") String id , @RequestBody EmailDTO Dto){
        try {
            String result = this.emailService.modifyOneEmail(id ,Dto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to update The email" + e.getMessage());
        }
    }
}