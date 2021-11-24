package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class GmailService {

    @Autowired
    JavaMailSender javaMailSender;


    public String send(){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("hojiakbar20300@gmail.com");
        message.setTo("xojiakbar1702@gmail.com");
        message.setSubject("Kod user!");
        message.setText("dffeef");
        javaMailSender.send(message);

        return "sending message";
    }


}
