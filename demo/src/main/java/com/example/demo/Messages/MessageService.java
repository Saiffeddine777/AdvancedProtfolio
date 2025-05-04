package com.example.demo.Messages;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public MessageDTO createMessageService (MessageDTO insertedMessage){
        try {
            Message dbInsertedMessage = this.messageRepository.save(Message.fromDTO(insertedMessage));
            return MessageDTO.fromEntity(dbInsertedMessage);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public List<MessageDTO> findAllMessages (){
        try {
            List<Message> messages = this.messageRepository.findAll();
            return messages.stream()
                           .map(MessageDTO::fromEntity)
                           .collect(Collectors.toList());
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());        }
    }

    @Transactional
    public Optional<MessageDTO> findOneMessage (String id){
        try {
            Optional<Message> messageIfFound = this.messageRepository.findById(id);
            return messageIfFound.map(MessageDTO::fromEntity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());    
        }
   
    }

    @Transactional
    public String destroyOneMessage (String id){
        try {
            this.messageRepository.deleteById(id);;
            return "Message has been deleted";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());    
        }
    }

    
    @Transactional
    public String modifyOneMessage (String id , MessageDTO data){
        try {
            if (data != null){
                Message messageToModify = this.messageRepository.findById(id).orElseThrow(()-> new RuntimeException("The Message was not Found !"));
                MessageDTO dtoToModify = MessageDTO.fromEntity(messageToModify);
                 if (data.getMessageText()!=null && !data.getMessageText().equals(dtoToModify.getMessageText())){
                    dtoToModify.setMessageText(data.getMessageText());
                 }

                 Message modified = Message.fromDTO(dtoToModify);
                 this.messageRepository.save(modified);

                 return "Message has been Modified"; 

            }
            return "There is nothing to modify";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());    
        }
    }

}