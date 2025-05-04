package com.example.demo.Messages;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection= "Messages")
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Getter
@Setter
public class Message {
 @Id
  private String id;
  private int idSender;
  private int idReceiver;
  private String MessageText; 

  public static Message fromDTO(MessageDTO messageDTO){
    return new Message(
      messageDTO.getId(),
      messageDTO.getIdSender(),
      messageDTO.getIdReceiver(),
      messageDTO.getMessageText()
    );
  }
}