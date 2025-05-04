package com.example.demo.Emails;


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
public class EmailDTO {
   private String id ; 
   private String subject;
   private String senderEmail;
   private String text;

   public static EmailDTO fromEntity(Email email){
     return new EmailDTO(
        email.getId(),
        email.getSubject(),
        email.getSenderEmail(),
        email.getText()
     );
   }
}