package com.example.demo.Messages;

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
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    public MessageController(MessageService messageService){
        this.messageService =messageService;
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> postMessageController(@RequestBody MessageDTO frontMessage){
        try {
           MessageDTO messageDTO = this.messageService.createMessageService(frontMessage);
           return ResponseEntity.status(HttpStatus.CREATED).body(messageDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to save the message :" + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getMessageController(){
        try {
           List<MessageDTO> messageDTOs = this.messageService.findAllMessages();
           return ResponseEntity.status(HttpStatus.OK).body(messageDTOs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to fetch messages" + e.getMessage());
        }
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<?> getOneMessageController(@PathVariable("id") String id){
        try {
           Optional<MessageDTO> optionalMessageDTO = this.messageService.findOneMessage(id);
           if (optionalMessageDTO.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalMessageDTO.get());
           }
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message Is not found");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to fetch The message" + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOneMessageController(@PathVariable("id") String id){
        try {
           String result = this.messageService.destroyOneMessage(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to fetch The message" + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOneMessageController(@PathVariable("id") String id , @RequestBody MessageDTO messageDTO){
        try {
           String result = this.messageService.modifyOneMessage(id , messageDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to update the Message" + e.getMessage());
        }
    }

}