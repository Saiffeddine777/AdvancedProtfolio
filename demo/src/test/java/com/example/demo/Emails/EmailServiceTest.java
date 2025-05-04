package com.example.demo.Emails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EmailServiceTest {
    @Mock
    private EmailRepository emailRepository;
    @InjectMocks
    private EmailService emailService;

    public EmailServiceTest (){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOneEmail (){
        Email email = new Email("1", "Subject", "sender@example.com", "Body");
        when(emailRepository.save(any(Email.class))).thenReturn(email);

        EmailDTO emailDTO = new EmailDTO("1", "Subject", "sender@example.com", "Body");
        EmailDTO result = emailService.createOneEmail(emailDTO);

        assertEquals(emailDTO, result);
        verify(emailRepository ,times(1)).save(any(Email.class));
    }
}