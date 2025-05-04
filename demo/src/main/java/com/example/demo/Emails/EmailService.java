package com.example.demo.Emails;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailService {
    private final EmailRepository emailRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    public EmailService(EmailRepository emailRepository){
       this.emailRepository = emailRepository;
    }

    @Transactional
    public EmailDTO createOneEmail(EmailDTO emailDTO){
        try {
            Email emailInserted = this.emailRepository.save(Email.fromDTO(emailDTO));
            return EmailDTO.fromEntity(emailInserted);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    } 
    @Transactional
    public List<EmailDTO> findAllEmails(){
        try {
            List<Email> emails = this.emailRepository.findAll();
            return emails.stream()
                         .map(EmailDTO::fromEntity)
                         .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    } 
    @Transactional
    public Optional<EmailDTO> findOneEmail(String id){
        try {
            Optional<Email> emailIfFound = this.emailRepository.findById(id);
            return emailIfFound.map(EmailDTO::fromEntity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    } 
    @Transactional
    public String destroyOneEmail(String id){
        try {
             this.emailRepository.deleteById(id);
            return "PEmail has been deleted";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    } 

    @Transactional
    public String modifyOneEmail(String id , EmailDTO data){
        try {
            if (data!=null){
                Email emailToModify= this.emailRepository.findById(id).orElseThrow(()->new RuntimeException("This email does not exists"));
                EmailDTO dtoToModify  = EmailDTO.fromEntity(emailToModify);
                if(data.getSubject() !=null && ! data.getSubject().equals(dtoToModify.getSubject())){
                     dtoToModify.setSubject(data.getSubject());
                }
                if(data.getText() !=null && ! data.getText().equals(dtoToModify.getText())){
                    dtoToModify.setText(data.getText());
                }

                this.emailRepository.save(Email.fromDTO(dtoToModify));
                return "Email has has been modified" ;
            }
            return "There is nothing to modify !";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    } 
}