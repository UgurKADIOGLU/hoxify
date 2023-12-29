package com.isg.ws.email;

import com.isg.ws.configuration.HoaxifyProperties;
import com.isg.ws.user.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {
    @Autowired
    HoaxifyProperties properties;

    @Value("${hoaxify.email.host}")
    String host;
    JavaMailSenderImpl mailSender;


    public void sendActivationMail(String email,String activationToken) {
        var activationUrl=properties.getClient().host()+"/activation"+activationToken;
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(properties.getEmail().from());
        mailMessage.setTo(email);
        mailMessage.setSubject("Account Activation");
        mailMessage.setText(activationUrl);
        this.mailSender.send(mailMessage);
    }
    @PostConstruct
    public void initialize(){
        this.mailSender=new JavaMailSenderImpl();
        mailSender.setHost(properties.getEmail().host());
        mailSender.setPort(properties.getEmail().port());
        mailSender.setUsername(properties.getEmail().username());
        mailSender.setPassword(properties.getEmail().password());

        Properties properties=mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable","true");

    }
}
