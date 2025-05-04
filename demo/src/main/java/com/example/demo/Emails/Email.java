package com.example.demo.Emails;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "emails")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Email {
   @Id
   private String id ; 
   private String subject;
   private String senderEmail;
   private String text;
   public static Email fromDTO(EmailDTO emailDTO){
    return new Email(
        emailDTO.getId(),
        emailDTO.getSubject(),
        emailDTO.getSenderEmail(),
        emailDTO.getText()
    );
  }
}