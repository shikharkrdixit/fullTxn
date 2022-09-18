package com.shikhar.transactionManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void setMailSender(String userMail,String subject, String text, String ccEmail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("shikharseupar@gmail.com");
        message.setTo(userMail);
        message.setSubject(subject);
        message.setCc(ccEmail);
        message.setText(text);
        mailSender.send(message);
    }


}
