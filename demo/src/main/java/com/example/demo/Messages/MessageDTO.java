package com.example.demo.Messages;

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
public class MessageDTO {
    private String id;
    private int idSender;
    private int idReceiver;
    private String MessageText; 

    public static MessageDTO fromEntity(Message message){
        return new MessageDTO(
            message.getId(),
            message.getIdSender(),
            message.getIdReceiver(),
            message.getMessageText()
        );
    }
    
}